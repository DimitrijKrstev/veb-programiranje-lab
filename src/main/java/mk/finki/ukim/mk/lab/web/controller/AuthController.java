package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/login", "/"})
@AllArgsConstructor
public class AuthController {
    UserRepository userRepository;

    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request){
        User user = userRepository.findByUsernameAndPassword(request.getParameter("username"), request.getParameter("password")).orElse(null);
        if (user == null) return "redirect:/login";

        request.getSession().setAttribute("user", user);
        return "redirect:/movies";
    }
}
