
package pl.gameChecker.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author mlethys
 */
@Controller
@SessionAttributes
public class RegisterController {
    
    @RequestMapping("/register")
    public String displayRegister() {
        return "register";
    }
    
    @RequestMapping(value = "/tryRegister", method = RequestMethod.POST)
    public String tryRegister(HttpServletRequest request, 
            @RequestParam(value = "login", required = true) String login,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "bday", required = true) String dateOfBirth,
            @RequestParam(value = "email", required = true) String email, 
            ModelMap model) {
        
        //todo
        model.addAttribute("username", login);
        return "registerSuccess";
    }
}
