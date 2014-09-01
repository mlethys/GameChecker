
package pl.gameChecker.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.gameChecker.model.hibernateEntities.Member;

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
        
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date registerDate = dateFormat.parse(dateFormat.format(date.getTime()));
            Date bDay = dateFormat.parse(dateOfBirth);
            java.sql.Date sqlRegisterDate = new java.sql.Date(registerDate.getTime());
            java.sql.Date sqlBDay = new java.sql.Date(bDay.getTime());
            Member newMember = new Member(login, password, sqlRegisterDate, email, sqlBDay);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        model.addAttribute("username", login);
        return "registerSuccess";
    }
}
