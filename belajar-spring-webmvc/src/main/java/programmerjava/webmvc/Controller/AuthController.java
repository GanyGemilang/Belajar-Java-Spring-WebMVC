package programmerjava.webmvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import programmerjava.webmvc.Models.User;

@Controller
public class AuthController {

    @PostMapping(path="/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> login(
           @RequestParam(name = "username") String username,
           @RequestParam(name = "password") String password,
           HttpServletResponse servletResponse,
           HttpServletRequest servletRequest
    ){
        if ("Gany".equals(username) && "rahasia".equals(password)) {

            HttpSession session = servletRequest.getSession(true);
            session.setAttribute("user", new User(username));

            jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("username", username);
            cookie.setPath("/");
            servletResponse.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.OK).body("Ok");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ko");
        }
    }

    @GetMapping(path= "/auth/user")
    public ResponseEntity<String> getUser(@CookieValue("username") String username){
        return ResponseEntity.ok("Hello " + username);
    }
}
