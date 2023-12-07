package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cart")
@AllArgsConstructor
public class CartController {
    ShoppingCartRepository shoppingCartRepository;

    @GetMapping
    public String getCart(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("user");
        ShoppingCart cart = shoppingCartRepository.findByUserUsername(user.getUsername()).orElse(null);

        if(cart == null) return "redirect:/login";

        model.addAttribute("cart", cart);
        return "cart";
    }
}
