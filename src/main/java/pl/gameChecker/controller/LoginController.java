package pl.gameChecker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mlethys
 */
@Controller
public class LoginController {
    
    @RequestMapping("/login")
    public String displayLogin() {
        return "login";
    }
}
