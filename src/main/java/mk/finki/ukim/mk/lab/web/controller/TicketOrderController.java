package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticketOrder")
@AllArgsConstructor
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;

    @GetMapping
    public String displayOrder(HttpServletRequest req, Model model, HttpServletRequest request){

        System.out.println(request.getSession().getAttribute("user"));

        model.addAttribute("order", TicketOrder.builder()
                .movieTitle(req.getParameter("movieTitle"))
                .numberOfTickets(Integer.parseInt(req.getParameter("numTickets"))).build());

        return "orderConfirmation";
    }

    @PostMapping
    public String placeOrder(HttpServletRequest req){

        System.out.println(req.getSession().getAttribute("user"));

        ticketOrderService.placeOrder(req.getParameter("movieTitle"),
                (User) req.getSession().getAttribute("user"),
                Integer.parseInt(req.getParameter("numTickets")));

        return "redirect:/cart";
    }

}
