
package pl.gameChecker.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.gameChecker.model.hibernateEntities.Member;
import pl.gameChecker.model.hibernateEntities.MemberDao;

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
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendar.setTime(dateFormat.parse(dateOfBirth));
            MemberDao memberDao = context.getBean("member", MemberDao.class);
            Member newMeber = new Member(login, 
                                            password, 
                                            email, 
                                            calendar.get(Calendar.DAY_OF_MONTH), 
                                            calendar.get(Calendar.MONTH), 
                                            calendar.get(Calendar.YEAR));
            if(memberDao.exists(newMeber)) {
                return "registerFailed";
            }
            memberDao.create(newMeber);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            return "registerFailed";
        }
        finally {
            context.close();
        }
        
        model.addAttribute("username", login);
        return "registerSuccess";
    }
}
