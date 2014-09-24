package pl.gameChecker.controller;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gameChecker.model.hibernateEntities.CompanyDao;
import pl.gameChecker.model.hibernateEntities.Game;
import pl.gameChecker.model.hibernateEntities.GameDao;
import pl.gameChecker.model.hibernateEntities.GamesLibrariesDao;
import pl.gameChecker.model.hibernateEntities.Gametype;
import pl.gameChecker.model.hibernateEntities.GametypeDao;
import pl.gameChecker.model.hibernateEntities.LibraryDao;
import pl.gameChecker.model.hibernateEntities.Member;
import pl.gameChecker.model.hibernateEntities.MemberDao;
import pl.gameChecker.model.hibernateEntities.MembersCPUDao;
import pl.gameChecker.model.hibernateEntities.MembersGPUDao;
import pl.gameChecker.model.hibernateEntities.MembersPC;
import pl.gameChecker.model.hibernateEntities.MembersPCDao;
import pl.gameChecker.model.hibernateEntities.MembersRatesAnswers;
import pl.gameChecker.model.hibernateEntities.MembersRatesAnswersDao;
import pl.gameChecker.model.hibernateEntities.MembersRatesGamesDao;
import pl.gameChecker.model.hibernateEntities.RoleDao;
import pl.gameChecker.model.hibernateEntities.SqfaAnswer;
import pl.gameChecker.model.hibernateEntities.SqfaAnswerCommentDao;
import pl.gameChecker.model.hibernateEntities.SqfaAnswerDao;
import pl.gameChecker.model.hibernateEntities.SqfaQuestion;
import pl.gameChecker.model.hibernateEntities.SqfaQuestionComment;
import pl.gameChecker.model.hibernateEntities.SqfaQuestionCommentDao;
import pl.gameChecker.model.hibernateEntities.SqfaQuestionDao;
import pl.gameChecker.model.pdf.PdfModel;

/**
 *
 * @author mlethys
 */
@Controller
public class LoginController {
    
    private static final ClassPathXmlApplicationContext CONTEXT = new ClassPathXmlApplicationContext("beans.xml");
    
    @RequestMapping("/login")
    public String displayLogin() {
        return "login";
    }
    
    @RequestMapping(value = "/tryLogin", method = RequestMethod.POST)
    public String tryLogin(HttpServletRequest request, 
            @RequestParam(value = "login", required = true) String login,
            @RequestParam(value = "password", required = true) String password, 
            ModelMap model) {
        
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        if(memberDao.isMemberLoginMatchPassword(login, password)) {
            model.addAttribute("username", login);
            request.getSession().setAttribute("loggedUser", login);
            return "loginSuccess";
        }
        return "loginFailed";
    }
    
    @RequestMapping("loggedIndex")
    public String loggedIndex(HttpServletRequest request, ModelMap model) {
        if(request.getSession().getAttribute("loggedUser") == null) {
            return "login";
        }
        
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member = memberDao.getByName(request.getSession().getAttribute("loggedUser").toString());
        model.addAttribute("memberRole", member.getRole().getName());
        return "loggedIndex";
    }
    
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loggedUser");
        return "index";
    }
    
    @RequestMapping("encyclopedia.html")
    public String encyclopedia(HttpServletRequest request, ModelMap model) {
        if(request.getSession().getAttribute("loggedUser") == null) {
            return "login";
        }
        GametypeDao gametypeDao = CONTEXT.getBean("gametype", GametypeDao.class);
        model.addAttribute("gameTypes", gametypeDao.getList());
        GameDao games = CONTEXT.getBean("game", GameDao.class);
        model.addAttribute("games", games.getList());
        return "encyclopedia";
    }
    @RequestMapping(value = "rate", method = RequestMethod.POST)
    public String rateGame(@RequestParam("rate[]") String[] rateRadios, 
                            @RequestParam("game") String gameToRate, 
                            HttpServletRequest request) {
        
        GameDao games = CONTEXT.getBean("game", GameDao.class);
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        MembersRatesGamesDao membersRatesGamesDao = CONTEXT.getBean("membersRatesGames", MembersRatesGamesDao.class);
        Member member = memberDao.getByName(request.getSession().getAttribute("loggedUser").toString());
        Game game = games.getByName(gameToRate);
        if(rateRadios.length > 0 && !membersRatesGamesDao.isMemberRatedGame(member, game)) {
            games.rateGame(member, game, Integer.parseInt(rateRadios[0]));
        }
        
        return "redirect:encyclopedia.html";
    }
    
    @RequestMapping("games")
    public String byGame(@RequestParam("game") String game, 
                                        ModelMap model, HttpServletRequest request) {
        GameDao games = CONTEXT.getBean("game", GameDao.class);
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member = memberDao.getByName(request.getSession().getAttribute("loggedUser").toString());
        MembersPCDao memberPCDao = CONTEXT.getBean("membersPC", MembersPCDao.class);
        if(!memberPCDao.getAllPCsOfMember(member).isEmpty()) {
            MembersPC membersPC = memberPCDao.getAllPCsOfMember(member).get(0);
            model.addAttribute("score", games.getGameVsPCResult(games.getByName(game), membersPC));
        }
        model.addAttribute("gameTitle", games.getByName(game).getName());
        model.addAttribute("gameDesc", games.getByName(game).getDescription());
        model.addAttribute("stars", games.getByName(game).getStars());
        return "game";
    }
    
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam(value="gameName", required=false, defaultValue="") String gameName,
                        @RequestParam(value="popularity[]", required=false, defaultValue="") String[] popularityRadios, 
                        @RequestParam(value="from", required=false, defaultValue="") String dateFrom,
                        @RequestParam(value="to", required=false, defaultValue="") String dateTo,
                        @RequestParam(value="types", required=false, defaultValue="") String type,
                        @RequestParam(value="others", required=false, defaultValue="") String[] singleplayer,
                        @RequestParam(value="others2", required=false, defaultValue="") String[] multiplayer,
                        @RequestParam(value="others3", required=false, defaultValue="") String[] free2play,
                        ModelMap model,
                        HttpServletRequest request) throws ParseException {
        
        GametypeDao gametypeDao = CONTEXT.getBean("gametype", GametypeDao.class);
        GameDao games = CONTEXT.getBean("game", GameDao.class);
        model.addAttribute("gameTypes", gametypeDao.getList());
        if(gameName.isEmpty() && (popularityRadios.length <= 0) 
                && dateFrom.isEmpty() 
                && dateTo.isEmpty() && (singleplayer.length <= 0) 
                && (multiplayer.length <= 0) && (free2play.length <= 0)) {

            model.addAttribute("games", games.getList());
            return "encyclopedia";
        }
        String strDateFrom;
        String strDateTo;
        if(dateFrom.isEmpty()) {
            strDateFrom = "1800-01-01";
        }
        else {
            strDateFrom = dateFrom + "-01-01";
        }
        if(dateTo.isEmpty()) {
            strDateTo = "2100-12-31";
        }
        else {
            strDateTo = dateTo + "-12-31";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tmpDate = sdf.parse(strDateFrom);
        java.sql.Date sqlDateFrom = new java.sql.Date(tmpDate.getTime());
        tmpDate = sdf.parse(strDateTo);
        java.sql.Date sqlDateTo = new java.sql.Date(tmpDate.getTime());
        boolean tmpSingleplayer = true;
        boolean tmpMultiplayer = true;
        boolean tmpFree2play = true;
        int gamePopularityLow = 0;
        int gamePopularityHigh = 100;
        if(singleplayer.length <= 0) {
            tmpSingleplayer = false;
        }
        if(multiplayer.length <= 0) {
           tmpMultiplayer = false;                 
        }
        if(free2play.length <= 0) {
           tmpFree2play = false;
        }
        if(popularityRadios.length > 0) {
            switch (popularityRadios[0]) {
                case "low":
                    gamePopularityLow = 0;
                    gamePopularityHigh = 33;
                    break;
                case "medium":
                    gamePopularityLow = 34;
                    gamePopularityHigh = 67;
                    break;
                case "high":
                    gamePopularityLow = 68;
                    gamePopularityHigh = 100;
                    break;
            }
        }
        
        
        model.addAttribute("games", games.getSearchGameResults(gameName, sqlDateFrom, sqlDateTo, 
                                    tmpSingleplayer, tmpMultiplayer, tmpFree2play, 
                                    1, 
                                    5, new Gametype(type), 
                                    gamePopularityLow, gamePopularityHigh));
        return "encyclopedia";
    }
    
    @RequestMapping("profile")
    public String displayProfile(HttpServletRequest request, ModelMap model) {
        
        if(request.getSession().getAttribute("loggedUser") == null) {
            return "login";
        }
        String userName = (String) request.getSession().getAttribute("loggedUser");
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        CompanyDao companyDao = CONTEXT.getBean("company", CompanyDao.class);
        MembersCPUDao membersCpuDao = CONTEXT.getBean("membersCPU", MembersCPUDao.class);
        MembersGPUDao membersGpuDao = CONTEXT.getBean("membersGPU", MembersGPUDao.class);
        Member member = memberDao.getByName(userName);
        model.addAttribute("role", member.getRole().getName());
        model.addAttribute("myLogin", member.getName());
        model.addAttribute("myEmail", member.getMail());
        model.addAttribute("myRegisterDate", member.getRegisterDate().toString());
        model.addAttribute("myAge", member.getAge());
        model.addAttribute("myPoints", member.getPoints());
        model.addAttribute("companies", companyDao.getList());
        model.addAttribute("cpus", membersCpuDao.getList());
        model.addAttribute("gpus", membersGpuDao.getList());
        MembersPCDao membersPCDao = CONTEXT.getBean("membersPC", MembersPCDao.class);
        
        if(!membersPCDao.getAllPCsOfMember(member).isEmpty()) {
            MembersPC membersPc = membersPCDao.getAllPCsOfMember(member).get(0);
            if(membersPc.getMembersCPU() != null) {
                model.addAttribute("myCpu", membersPc.getMembersCPU().getName() + " " + membersPc.getMembersCPU().getCompany().getName());
            }
            if(membersPc.getMembersGPU() != null) {
                model.addAttribute("myGpu", membersPc.getMembersGPU().getName() + " " + membersPc.getMembersGPU().getCompany().getName());
            }
            model.addAttribute("myRam", membersPc.getMemory());
        }
        
                
        if(member.getAvatarUrl() == null || member.getAvatarUrl().isEmpty()) {
             model.addAttribute("usersAvatar", "resources/images/default_av.jpg");
        }
        else {
            model.addAttribute("usersAvatar", member.getAvatarUrl());
        }
        
        
        return "myProfile";
    }
    
    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public String editProfile(@RequestParam(value="newUsername")String newUsername,
                                @RequestParam(value="newEmail")String newEmail,
                                @RequestParam(value="newAvatar") String newAvatarURL,
                                ModelMap model, HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("loggedUser");
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member = memberDao.getByName(userName);
        if(!newUsername.isEmpty()) {
            member.setName(newUsername);
            memberDao.update(member);
            if(!newEmail.isEmpty()) {
                member.setMail(newEmail);
                memberDao.update(member);
            }
            return "redirect:logout";
        }
        if(!newEmail.isEmpty()) {
            member.setMail(newEmail);
            memberDao.update(member);
        }
        if(!newAvatarURL.isEmpty()) {
            member.setAvatarUrl(newAvatarURL);
            memberDao.update(member);
        }
        
        if(member.getAvatarUrl() == null || member.getAvatarUrl().isEmpty()) {
             model.addAttribute("usersAvatar", "resources/images/default_av.jpg");
        }
        else {
            model.addAttribute("usersAvatar", member.getAvatarUrl());
        }
        return "redirect:profile";
    }
    
    @RequestMapping(value = "/editCpu", method = RequestMethod.GET)
    public String editCpu(@RequestParam(value="cpuNames", required = true) String newCpuName,
                            ModelMap model, HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("loggedUser");

        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        MembersPCDao memberPCDao = CONTEXT.getBean("membersPC", MembersPCDao.class);
        MembersCPUDao membersCpuDao = CONTEXT.getBean("membersCPU", MembersCPUDao.class);

        Member member = memberDao.getByName(userName);
        MembersPC membersPc = new MembersPC(member);
        if(memberPCDao.getAllPCsOfMember(member).isEmpty()) {
            membersPc.setMembersCPU(membersCpuDao.getByName(newCpuName));
            memberPCDao.create(membersPc);
        }
        else {
            MembersPC memberNewPc = memberPCDao.getAllPCsOfMember(member).get(0);
            memberNewPc.setMembersCPU(membersCpuDao.getByName(newCpuName));
            memberPCDao.update(memberNewPc);
            
        }
        return "redirect:profile";
    }
    
    @RequestMapping(value = "/editGpu", method = RequestMethod.GET)
    public String editGpu(@RequestParam(value="gpuNames", required = true) String newGpuName,
                            ModelMap model, HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("loggedUser");

        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        MembersPCDao memberPCDao = CONTEXT.getBean("membersPC", MembersPCDao.class);
        MembersGPUDao membersGpuDao = CONTEXT.getBean("membersGPU", MembersGPUDao.class);

        Member member = memberDao.getByName(userName);
        MembersPC membersPc = new MembersPC(member);
        if(memberPCDao.getAllPCsOfMember(member).isEmpty()) {
            membersPc.setMembersGPU(membersGpuDao.getByName(newGpuName));
            memberPCDao.create(membersPc);
        }
        else {
            MembersPC memberNewPc = memberPCDao.getAllPCsOfMember(member).get(0);
            memberNewPc.setMembersGPU(membersGpuDao.getByName(newGpuName));
            memberPCDao.update(memberNewPc);
        }
        return "redirect:profile";
    }
    
    @RequestMapping(value = "/editRam", method = RequestMethod.GET)
    public String editRam(@RequestParam(value="newRamMemory", required = true) String newRamMemory,
                            ModelMap model, HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("loggedUser");

        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        MembersPCDao memberPCDao = CONTEXT.getBean("membersPC", MembersPCDao.class);

        Member member = memberDao.getByName(userName);
        MembersPC membersPc = new MembersPC(member);
        if(memberPCDao.getAllPCsOfMember(member).isEmpty()) {
            membersPc.setMemory(Integer.parseInt(newRamMemory));
            memberPCDao.create(membersPc);
        }
        else {
            MembersPC memberNewPc = memberPCDao.getAllPCsOfMember(member).get(0);
            memberNewPc.setMemory(Integer.parseInt(newRamMemory));
            memberPCDao.update(memberNewPc);
        }
        return "redirect:profile";
    }

    @RequestMapping("library")
    public String usersLibrary(@RequestParam("user") String user, 
                                        ModelMap model, HttpServletRequest request) {
        
        model.addAttribute("loggedUser", request.getSession().getAttribute("loggedUser"));
        request.getSession().setAttribute("libraryOwner", user);
        model.addAttribute("usersLibrary", user);
        
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member memberLogged = memberDao.getByName(request.getSession().getAttribute("loggedUser").toString());
        model.addAttribute("loggedRole", memberLogged.getRole().getName());
        Member member = memberDao.getByName(user);
        GameDao gameDao = CONTEXT.getBean("game", GameDao.class);
        model.addAttribute("availableGames", gameDao.getList());
        model.addAttribute("usersGames", gameDao.getGamesFromMember(member));
        model.addAttribute("usersGamesToRemove", gameDao.getGamesFromMember(member));
        return "library";
    }
    
    @RequestMapping(value = "/addGame", method = RequestMethod.POST)
    public String addGame(@RequestParam(value="availableGames") String newGame,
                            HttpServletRequest request, 
                            ModelMap model) {
        
        LibraryDao libraryDao = CONTEXT.getBean("library", LibraryDao.class);
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        GameDao gameDao = CONTEXT.getBean("game", GameDao.class);
        GamesLibrariesDao gamesLibrariesDao = CONTEXT.getBean("gamesLibraries", GamesLibrariesDao.class);
        String user = request.getSession().getAttribute("libraryOwner").toString();
        if(gamesLibrariesDao.isMemberGotGameInLibrary(memberDao.getByName(user), gameDao.getByName(newGame))) {
            return "redirect:library?user=" + user;
        }
        libraryDao.addGameToMembersLibrary(memberDao.getByName(user), gameDao.getByName(newGame));
        return "redirect:library?user=" + user;
    }
    
    @RequestMapping(value = "/removeGame", method = RequestMethod.POST)
    public String removeGame(@RequestParam(value="usersGames") String gameToRemove,
                                HttpServletRequest request, 
                                ModelMap model) {
        
        LibraryDao libraryDao = CONTEXT.getBean("library", LibraryDao.class);
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        GameDao gameDao = CONTEXT.getBean("game", GameDao.class);
        String user = request.getSession().getAttribute("libraryOwner").toString();
        libraryDao.removeGameFromMembersLibrary(memberDao.getByName(user), gameDao.getByName(gameToRemove));
        gameDao.updateGamePopularity(gameDao.getByName(gameToRemove));
        return "redirect:library?user=" + user;
    }
    
    @RequestMapping(value="searchInLibrary", method = RequestMethod.POST)
    public String searchLibrary(@RequestParam("gameName") String gameName,
                                    @RequestParam("user") String user, 
                                    ModelMap model, HttpServletRequest request) {
        model.addAttribute("loggedUser", request.getSession().getAttribute("loggedUser"));
        request.getSession().setAttribute("libraryOwner", user);
        model.addAttribute("usersLibrary", user);
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member = memberDao.getByName(user);
        Member memberLogged = memberDao.getByName(request.getSession().getAttribute("loggedUser").toString());
        model.addAttribute("loggedRole", memberLogged.getRole().getName());
        GameDao gameDao = CONTEXT.getBean("game", GameDao.class);
        model.addAttribute("availableGames", gameDao.getList());
        model.addAttribute("usersGames", gameDao.getByNameAndMember(gameName, member));
        model.addAttribute("usersGamesToRemove", gameDao.getGamesFromMember(member));
        return "library";
    }
    
    @RequestMapping(value="deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("users") String username) {
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        SqfaQuestionDao sqfaQuestionDao = CONTEXT.getBean("sqfaQuestion", SqfaQuestionDao.class);
        SqfaAnswerDao sqfaAnswerDao = CONTEXT.getBean("sqfaAnswer", SqfaAnswerDao.class);
        SqfaQuestionCommentDao sqfaQuestionCommentDao = CONTEXT.getBean("sqfaQuestionComment", SqfaQuestionCommentDao.class);
        SqfaAnswerCommentDao sqfaAnswerCommentDao = CONTEXT.getBean("sqfaAnswerComment", SqfaAnswerCommentDao.class);
        MembersRatesGamesDao membersRatesGamesDao = CONTEXT.getBean("membersRatesGames", MembersRatesGamesDao.class);
        MembersRatesAnswersDao membersRatesAnswersDao = CONTEXT.getBean("membersRatesAnswers", MembersRatesAnswersDao.class);
        Member member = memberDao.getByName(username);
        memberDao.delete(member, sqfaAnswerDao.getAllAnswersFromMember(member), 
                            sqfaQuestionDao.getAllQuestionsFromMember(member), 
                            sqfaQuestionCommentDao.getAllQuestionCommentsFromMember(member), 
                            sqfaAnswerCommentDao.getAllAnswerCommentsFromMember(member), 
                            membersRatesGamesDao.getMembersRatesGamesByMember(member), 
                            membersRatesAnswersDao.getMembersRatesGamesByMember(member));
        return "redirect:adminPanel";
    }
    
    @RequestMapping(value="changeUserName", method = RequestMethod.POST)
    public String changeUsername(@RequestParam("users") String username,
                                    @RequestParam("newUsername") String newUsername) {
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member =  memberDao.getByName(username);
        member.setName(newUsername);
        memberDao.update(member);
        return "redirect:adminPanel";
    }
    
    @RequestMapping(value="deleteAvatar", method = RequestMethod.POST)
    public String deleteAvatar(@RequestParam("users") String username) {
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member =  memberDao.getByName(username);
        member.setAvatarUrl("resources/images/default_av.jpg");
        memberDao.update(member);
        return "redirect:adminPanel";
    }
    
    @RequestMapping(value="deleteGame", method = RequestMethod.POST)
    public String deleteGame(@RequestParam("games") String game) {
        GameDao gameDao = CONTEXT.getBean("game", GameDao.class);
        
        gameDao.delete(gameDao.getByName(game));
        return "redirect:adminPanel";
    }
    
    @RequestMapping(value="setRole", method = RequestMethod.POST)
    public String setRole(@RequestParam("users") String user,
                            @RequestParam("roles") String roleName) {
        
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        RoleDao roleDao = CONTEXT.getBean("role", RoleDao.class);
        Member member =  memberDao.getByName(user);
        member.setRole(roleDao.getByName(roleName));
        memberDao.update(member);
        return "redirect:adminPanel";
    }
    
    @RequestMapping(value = "adminAddGame", method = RequestMethod.POST)
    public String addGameToEncyclopedia(@RequestParam(value="types", required=true) String type,
                                        @RequestParam(value="gameRelease", required=true) String releaseDate,
                                        @RequestParam(value="others", required=false, defaultValue="") String[] singleplayer,
                                        @RequestParam(value="others2", required=false, defaultValue="") String[] multiplayer,
                                        @RequestParam(value="others3", required=false, defaultValue="") String[] free2play,
                                        @RequestParam(value="gameTitle", required=true) String gameTitle,
                                        @RequestParam(value="gameDescription", required=true) String gameDescription) throws ParseException {
        
        GameDao gameDao = CONTEXT.getBean("game", GameDao.class);
        GametypeDao gametypeDao = CONTEXT.getBean("gametype", GametypeDao.class);
        boolean tmpSingleplayer = true;
        boolean tmpMultiplayer = true;
        boolean tmpFree2play = true;
        if(singleplayer.length <= 0) {
            tmpSingleplayer = false;
        }
        if(multiplayer.length <= 0) {
           tmpMultiplayer = false;                 
        }
        if(free2play.length <= 0) {
           tmpFree2play = false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tmpDate = sdf.parse(releaseDate);
        java.sql.Date sqlDate = new java.sql.Date(tmpDate.getTime());
        Game game = new Game(gameTitle, sqlDate, tmpSingleplayer, tmpMultiplayer, tmpFree2play, gameDescription, gametypeDao.getByName(type));
        gameDao.create(game);
        return "redirect:adminPanel";
    }
    
    @RequestMapping(value="createNewMembersRaport", method = RequestMethod.POST)
    public String createNewMembersRaport(@RequestParam(value="newMembersFrom", required=true) String newMembersFrom,
                                            @RequestParam(value="newMembersTo", required=true) String newMembersTo) throws ParseException {
        
        PdfModel pdf = CONTEXT.getBean("pdfModel", PdfModel.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tmpDate = sdf.parse(newMembersFrom);
        java.sql.Date sqlDateFrom = new java.sql.Date(tmpDate.getTime());
        tmpDate = sdf.parse(newMembersTo);
        java.sql.Date sqlDateTo = new java.sql.Date(tmpDate.getTime());
        try {
            pdf.createNewMembersRaport(sqlDateFrom, sqlDateTo);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:adminPanel";
    }
    
    @RequestMapping(value="createGameAdditionsRaport", method = RequestMethod.POST)
    public String createGameAdditionsRaport(@RequestParam(value="gameAdditionsFrom", required=true) String gameAdditionsFrom) throws ParseException {
        PdfModel pdf = CONTEXT.getBean("pdfModel", PdfModel.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tmpDate = sdf.parse(gameAdditionsFrom);
        java.sql.Date sqlDateFrom = new java.sql.Date(tmpDate.getTime());
        try {
            pdf.createGamesAdditionsReport(sqlDateFrom);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:adminPanel";
    }
    
    @RequestMapping(value="createSqfaSummaryRaport", method = RequestMethod.POST)
    public String createSqfaSummaryRaport(@RequestParam(value="sqfaSummaryFrom", required=true) String sqfaSummaryFrom,
                                            @RequestParam(value="sqfaSummaryTo", required=true) String sqfaSummaryTo) throws ParseException {
        
        PdfModel pdf = CONTEXT.getBean("pdfModel", PdfModel.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tmpDate = sdf.parse(sqfaSummaryFrom);
        java.sql.Date sqlDateFrom = new java.sql.Date(tmpDate.getTime());
        tmpDate = sdf.parse(sqfaSummaryTo);
        java.sql.Date sqlDateTo = new java.sql.Date(tmpDate.getTime());
        try {
            pdf.createSqfaSummaryReport(sqlDateFrom, sqlDateTo);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:adminPanel";
    }
  
    @RequestMapping("adminPanel")
    public String displayAdminPanel(ModelMap model) {
        
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        GameDao gameDao = CONTEXT.getBean("game", GameDao.class);
        GametypeDao gametypeDao = CONTEXT.getBean("gametype", GametypeDao.class);
        model.addAttribute("gameTypes", gametypeDao.getList());
        model.addAttribute("members", memberDao.getList());
        model.addAttribute("games", gameDao.getList());
        RoleDao roleDao = CONTEXT.getBean("role", RoleDao.class);
        model.addAttribute("roles", roleDao.getList());
        return "adminPanel";
    }
    
    @RequestMapping("sqfa.html")
    public String displaySqfa(HttpServletRequest request, ModelMap model) {
        if(request.getSession().getAttribute("loggedUser") == null) {
            return "login";
        }
        SqfaQuestionDao sqfaQuestionDao = CONTEXT.getBean("sqfaQuestion", SqfaQuestionDao.class);
        SqfaAnswerDao sqfaAnswerDao = CONTEXT.getBean("sqfaAnswer", SqfaAnswerDao.class);
        List<SqfaQuestion> questions = sqfaQuestionDao.getList();
        for(SqfaQuestion question : questions) {
            System.out.println(sqfaAnswerDao.getAnswersFromQuestion(question).size());
            question.setAnswersCount(sqfaAnswerDao.getAnswersFromQuestion(question).size());
        }
        model.addAttribute("questions", questions);
        return "sqfa";
    }
    
    @RequestMapping(value = "searchSqfa", method = RequestMethod.POST)
    public String searchSqfa(@RequestParam("questionTitle") String questionTitle, ModelMap model) {
        
        SqfaQuestionDao sqfaQuestionDao = CONTEXT.getBean("sqfaQuestion", SqfaQuestionDao.class);
        SqfaAnswerDao sqfaAnswerDao = CONTEXT.getBean("sqfaAnswer", SqfaAnswerDao.class);
        List<SqfaQuestion> questions = sqfaQuestionDao.getWhereTitleLike(questionTitle);
        for(SqfaQuestion question : questions) {
            question.setAnswersCount(sqfaAnswerDao.getAnswersFromQuestion(question).size());
        }
        model.addAttribute("questions", questions);
        return "sqfa";
    }
    
    @RequestMapping("questions")
    public String displayQuestion(@RequestParam("question") String questionTitle, 
                                    ModelMap model,HttpServletRequest request) {
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        model.addAttribute("loggedUser", memberDao.getByName(request.getSession().getAttribute("loggedUser").toString()));
        SqfaQuestionDao sqfaQuestionDao = CONTEXT.getBean("sqfaQuestion", SqfaQuestionDao.class);
        SqfaAnswerDao sqfaAnswerDao = CONTEXT.getBean("sqfaAnswer", SqfaAnswerDao.class);
        SqfaQuestion sqfaQuestion = sqfaQuestionDao.getByTitle(questionTitle);
        SqfaQuestionCommentDao sqfaQuestionCommentDao = CONTEXT.getBean("sqfaQuestionComment", SqfaQuestionCommentDao.class);
        Member author = sqfaQuestion.getMember();
        model.addAttribute("question", sqfaQuestion);
        model.addAttribute("author", author);
        model.addAttribute("answers", sqfaAnswerDao.getAnswersFromQuestion(sqfaQuestion));
        model.addAttribute("questionComments", sqfaQuestionCommentDao.getCommentsFromQuestion(sqfaQuestion));
        return "question";
    }
    
    @RequestMapping(value="addComment", method = RequestMethod.POST)
    public String addComment(@RequestParam("question") String questionTitle,
                                @RequestParam("comment") String comment, HttpServletRequest request) {
        
        if(comment == null || comment.isEmpty()) {
            return "redirect:questions?question=" + questionTitle;
        }
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member = memberDao.getByName(request.getSession().getAttribute("loggedUser").toString());
        SqfaQuestionDao sqfaQuestionDao = CONTEXT.getBean("sqfaQuestion", SqfaQuestionDao.class);
        SqfaQuestion sqfaQuestion = sqfaQuestionDao.getByTitle(questionTitle);
        SqfaQuestionCommentDao sqfaQuestionCommentDao = CONTEXT.getBean("sqfaQuestionComment", SqfaQuestionCommentDao.class);
        SqfaQuestionComment questionComment = new SqfaQuestionComment(member, sqfaQuestion, comment);
        sqfaQuestionCommentDao.create(questionComment);
        return "redirect:questions?question=" + questionTitle;
    }
    
    @RequestMapping(value="addAnswer", method = RequestMethod.POST)
    public String addAnswer(@RequestParam("question") String questionTitle,
                                @RequestParam("answer") String answer, HttpServletRequest request) {
    
        if(answer == null || answer.isEmpty()) {
            return "redirect:questions?question=" + questionTitle;
        }
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member = memberDao.getByName(request.getSession().getAttribute("loggedUser").toString());
        SqfaQuestionDao sqfaQuestionDao = CONTEXT.getBean("sqfaQuestion", SqfaQuestionDao.class);
        SqfaQuestion sqfaQuestion = sqfaQuestionDao.getByTitle(questionTitle);
        SqfaAnswerDao sqfaAnswerDao = CONTEXT.getBean("sqfaAnswer", SqfaAnswerDao.class);
        SqfaAnswer questionAnswer = new SqfaAnswer(member, sqfaQuestion, answer);
        sqfaAnswerDao.create(questionAnswer);
        return "redirect:questions?question=" + questionTitle;
    }
    
    @RequestMapping(value="rateAnswer", method = RequestMethod.POST)
    public String rateAnswer(@RequestParam("id") String id,
                                @RequestParam("question") String question,
                                HttpServletRequest request) {
        
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member = memberDao.getByName(request.getSession().getAttribute("loggedUser").toString());
        SqfaAnswerDao sqfaAnswerDao = CONTEXT.getBean("sqfaAnswer", SqfaAnswerDao.class);
        SqfaAnswer answer = sqfaAnswerDao.getById(Integer.parseInt(id));
        MembersRatesAnswersDao membersRatesAnswersDao = CONTEXT.getBean("membersRatesAnswers", MembersRatesAnswersDao.class);
        MembersRatesAnswers rates = new MembersRatesAnswers(true, member, answer);
        if(!membersRatesAnswersDao.isMemberRatedAnswer(member,answer)) {
            membersRatesAnswersDao.create(rates);
            sqfaAnswerDao.incrementSqfaAnswerPoints(answer);

            System.out.println("wszedl do rated answer if");
        }
        return "redirect:questions?question=" + question;
    }
    
    @RequestMapping("deleteQuestion")
    public String deleteQuestion(@RequestParam("id") String id) {
        
        SqfaQuestionDao sqfaQuestionDao = CONTEXT.getBean("sqfaQuestion", SqfaQuestionDao.class);
        SqfaQuestion sqfaQuestion = sqfaQuestionDao.getById(Integer.parseInt(id));
        sqfaQuestionDao.delete(sqfaQuestion); 
        return "redirect:sqfa.html";
    }
   
    @RequestMapping("deleteComment")
    public String deleteComment(@RequestParam("id") String id) {
        
        SqfaQuestionCommentDao sqfaQuestionCommentDao = CONTEXT.getBean("sqfaQuestionComment", SqfaQuestionCommentDao.class);
        SqfaQuestionComment comment = sqfaQuestionCommentDao.getById(Integer.parseInt(id));
        sqfaQuestionCommentDao.delete(comment);
        return "redirect:sqfa.html";
    }
    
    @RequestMapping("deleteAnswer")
    public String deleteAnswer(@RequestParam("id") String id) {
        
        SqfaAnswerDao sqfaAnswerDao = CONTEXT.getBean("sqfaAnswer", SqfaAnswerDao.class);
        SqfaAnswer questionAnswer = sqfaAnswerDao.getById(Integer.parseInt(id));
        sqfaAnswerDao.delete(questionAnswer);
        return "redirect:sqfa.html";
    }
    
    @RequestMapping(value = "addQuestion", method = RequestMethod.POST)
    public String addQuestion(@RequestParam("questionTitle") String questionTitle,
                                @RequestParam("questionContent") String questionContent,
                                HttpServletRequest request) {
        
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member = memberDao.getByName(request.getSession().getAttribute("loggedUser").toString());
        SqfaQuestionDao sqfaQuestionDao = CONTEXT.getBean("sqfaQuestion", SqfaQuestionDao.class);
        SqfaQuestion question = new SqfaQuestion(member, questionTitle, questionContent);
        sqfaQuestionDao.create(question);
        return "redirect:sqfa.html";
    }
    
    @RequestMapping("usrProfile")
    public String displayUserProfile(@RequestParam("user") String user, HttpServletRequest request, ModelMap model) {
        
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member = memberDao.getByName(user);
        if(member.getName().equals(request.getSession().getAttribute("loggedUser").toString())) {
            return "myProfile";
        }
        model.addAttribute("member", member);
        model.addAttribute("age", member.getAge());
        MembersPCDao membersPCDao = CONTEXT.getBean("membersPC", MembersPCDao.class);
        if(!membersPCDao.getAllPCsOfMember(member).isEmpty()) {
            MembersPC membersPc = membersPCDao.getAllPCsOfMember(member).get(0);
            if(membersPc.getMembersCPU() != null) {
                model.addAttribute("cpu", membersPc.getMembersCPU().getName() + " " + membersPc.getMembersCPU().getCompany().getName());
            }
            if(membersPc.getMembersGPU() != null) {
                model.addAttribute("gpu", membersPc.getMembersGPU().getName() + " " + membersPc.getMembersGPU().getCompany().getName());
            }
            model.addAttribute("ram", membersPc.getMemory());
        }
        
        return "profile";
    }
    
    @RequestMapping("help.html")
    public String displayHelp() {
        return "help";
    }
}
