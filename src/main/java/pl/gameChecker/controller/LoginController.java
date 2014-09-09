package pl.gameChecker.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gameChecker.model.hibernateEntities.MemberDao;

/**
 *
 * @author mlethys
 */
@Controller
public class LoginController {
    
    private static ClassPathXmlApplicationContext CONTEXT = new ClassPathXmlApplicationContext("beans.xml");
    
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
}
