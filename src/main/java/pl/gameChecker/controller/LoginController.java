package pl.gameChecker.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gameChecker.model.hibernateEntities.Company;
import pl.gameChecker.model.hibernateEntities.GameDao;
import pl.gameChecker.model.hibernateEntities.GametypeDao;
import pl.gameChecker.model.hibernateEntities.Member;
import pl.gameChecker.model.hibernateEntities.MemberDao;
import pl.gameChecker.model.hibernateEntities.MembersCPU;
import pl.gameChecker.model.hibernateEntities.MembersCPUDao;
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
    
    @RequestMapping("games")
    public String byGame(@RequestParam("game") String game, 
                                        ModelMap model) {
        GameDao games = CONTEXT.getBean("game", GameDao.class);
        model.addAttribute("gameTitle", games.getByName(game).getName());
        model.addAttribute("gameDesc", games.getByName(game).getDescription());
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
                        HttpServletRequest request) {
        
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
        return "encyclopedia";
    }
    
    @RequestMapping("profile")
    public String displayProfile(HttpServletRequest request, ModelMap model) {
        
        if(request.getSession().getAttribute("loggedUser") == null) {
            return "login";
        }
        String userName = (String) request.getSession().getAttribute("loggedUser");
        MemberDao memberDao = CONTEXT.getBean("member", MemberDao.class);
        Member member = memberDao.getByName(userName);
        model.addAttribute("myLogin", member.getName());
        model.addAttribute("myEmail", member.getMail());
        model.addAttribute("myRegisterDate", member.getRegisterDate().toString());
        model.addAttribute("myAge", member.getAge());
        model.addAttribute("myPoints", member.getPoints());
        MembersPCDao membersPCDao = CONTEXT.getBean("membersPC", MembersPCDao.class);
        if(!membersPCDao.getAllPCsOfMember(member).isEmpty()) {
             MembersPC memberPC = membersPCDao.getAllPCsOfMember(member).get(0);
            model.addAttribute("myCpu", memberPC.getMembersCPU().getName());
            model.addAttribute("myGpu", memberPC.getMembersGPU().getName());
            model.addAttribute("myRam", memberPC.getMemory());
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
        
        model.addAttribute("myLogin", member.getName());
        model.addAttribute("myEmail", member.getMail());
        model.addAttribute("myRegisterDate", member.getRegisterDate().toString());
        model.addAttribute("myAge", member.getAge());
        model.addAttribute("myPoints", member.getPoints());
        MembersPCDao membersPC = CONTEXT.getBean("membersPC", MembersPCDao.class);
        if(!member.getMembersPCs().isEmpty()) {
            MembersPC memberPC = membersPC.getAllPCsOfMember(member).get(0);
            model.addAttribute("myCpu", memberPC.getMembersCPU().getName());
            model.addAttribute("myGpu", memberPC.getMembersGPU().getName());
            model.addAttribute("myRam", memberPC.getMemory());
        }
        
        if(member.getAvatarUrl() == null || member.getAvatarUrl().isEmpty()) {
             model.addAttribute("usersAvatar", "resources/images/default_av.jpg");
        }
        else {
            model.addAttribute("usersAvatar", member.getAvatarUrl());
        }
        return "myProfile";
    }
    
    @RequestMapping(value="/editCPU", method = RequestMethod.POST)
    public String editCpu(@RequestParam(value="newCpuName")String newCpuName,
                            @RequestParam(value="newCpuDate")String newCpuDate,
                            @RequestParam(value="newCpuCompany") String newCpuCompany,
                            ModelMap model, HttpServletRequest request) {
        
        try {
            if(newCpuName.isEmpty() & newCpuDate.isEmpty() & newCpuCompany.isEmpty()) {
                return "myProfile";
            }
            MembersCPUDao membersCPUDao = CONTEXT.getBean("membersCPU", MembersCPUDao.class);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(newCpuDate);
            MembersCPU membersCPU = new MembersCPU(newCpuName, new java.sql.Date(date.getTime()), new Company(newCpuCompany));
            membersCPUDao.update(membersCPU);
        } catch (ParseException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "myProfile";
    }

}
