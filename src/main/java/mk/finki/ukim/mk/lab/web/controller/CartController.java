package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cart")
@AllArgsConstructor
public class CartController {
    ShoppingCartRepository shoppingCartRepository;
    UserService userService;

    @GetMapping
    public String getCart(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("user");
        ShoppingCart cart = shoppingCartRepository.findByUserUsername(user.getUsername()).orElse(null);
        System.out.println("CART2" + cart);

        if(cart == null) return "redirect:/login";

        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        System.out.println(cart.getTicketOrders());
        model.addAttribute("orders", cart.getTicketOrders());

        return "cart";
    }
}
