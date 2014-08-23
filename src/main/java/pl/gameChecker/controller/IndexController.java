package pl.gameChecker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mlethys
 */
@Controller
public class IndexController {
    
    @RequestMapping("/")
    public String index(@RequestParam(value="newsTitle", required=false, defaultValue="News title")String newsTitle,
                        @RequestParam(value="newsContent", required=false, defaultValue="News content")String newsContent, 
                        Model model) {
        model.addAttribute("newsTitle", newsTitle);
        model.addAttribute("newsContent", newsContent);
        return "";
    }
}
