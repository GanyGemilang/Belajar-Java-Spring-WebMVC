package programmerjava.webmvc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import programmerjava.webmvc.Models.User;

@Controller
public class UserController {

    @GetMapping(path="/user/current")
    @ResponseBody
    public String getUser(@SessionAttribute(name= "user") User user){
        return  "Hello " + user.getUsername();
    }
}
