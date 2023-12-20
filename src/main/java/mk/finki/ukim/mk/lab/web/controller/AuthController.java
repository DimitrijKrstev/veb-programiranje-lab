package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/login", "/"})
@AllArgsConstructor
public class AuthController {
    UserService userService;

    @GetMapping
    public String getLoginPage(HttpServletRequest request){
        request.getSession().setAttribute("user", userService.getUserByUsername("dimi"));
        System.out.println(request.getSession().getAttribute("user"));
        return request.getSession().getAttribute("user") == null ? "login" : "redirect:/movies";
    }

    @PostMapping
    public String login(HttpServletRequest request){
        User user = userService.login(request.getParameter("username"), request.getParameter("password"));
        if (user == null) return "redirect:/login";

        request.getSession().setAttribute("user", user);
        return "redirect:/movies";
    }
}
