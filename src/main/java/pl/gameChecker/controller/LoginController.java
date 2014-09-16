package pl.gameChecker.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gameChecker.model.hibernateEntities.CompanyDao;
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
    public String loggedIndex(HttpServletRequest request) {
        if(request.getSession().getAttribute("loggedUser") == null) {
            return "login";
        }
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
                            @RequestParam("game") String gameToRate) {
        
        GameDao games = CONTEXT.getBean("game", GameDao.class);
        if(rateRadios.length > 0) {
            games.rateGame(games.getByName(gameToRate), Integer.parseInt(rateRadios[0]));
        }
        
        return "redirect:encyclopedia.html";
    }
    
    @RequestMapping("games")
    public String byGame(@RequestParam("game") String game, 
                                        ModelMap model) {
        GameDao games = CONTEXT.getBean("game", GameDao.class);
        model.addAttribute("gameTitle", games.getByName(game).getName());
        model.addAttribute("gameDesc", games.getByName(game).getDescription());
        model.addAttribute("stars", games.getByName(game).getStars());
        return "game";
    }
    
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam(value="gameName", required=false, defaultValue="") String gameName,
                        @RequestParam(value="popularity[]", required=false, defaultValue="") String[] popularityRadios, 
                        @RequestParam(value="rateFrom", required=false, defaultValue="") String rateFrom,
                        @RequestParam(value="rateTo", required=false, defaultValue="") String rateTo,
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
                && (rateFrom.equals(rateTo)) && dateFrom.isEmpty() 
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
                                    Double.parseDouble(rateFrom), 
                                    Double.parseDouble(rateTo), new Gametype(type), 
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
        Member member = memberDao.getByName(user);
        GameDao gameDao = CONTEXT.getBean("game", GameDao.class);
        model.addAttribute("availableGames", gameDao.getList());
        model.addAttribute("usersGames", gameDao.getGamesFromMember(member));
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
    
}
