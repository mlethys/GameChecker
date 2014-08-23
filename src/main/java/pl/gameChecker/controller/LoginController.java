package pl.gameChecker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.gameChecker.model.UsersLogin;

/**
 *
 * @author mlethys
 */
@Controller
public class LoginController {
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login", "command", new UsersLogin());
    }
    
    @RequestMapping(value = "/logged", method = RequestMethod.POST)
    public String logged(@ModelAttribute("SpringWeb")UsersLogin login, 
                        ModelMap model) {
        model.addAttribute("Login", login.getUsername());
        return "result";
    }
}
