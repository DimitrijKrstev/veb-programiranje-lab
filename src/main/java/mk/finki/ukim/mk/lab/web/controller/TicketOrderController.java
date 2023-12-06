package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticketOrder")
@AllArgsConstructor
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;

    @GetMapping
    public String displayOrder(HttpServletRequest req, Model model){

        if(req.getParameter("movieTitle") != null) model.addAttribute("order",
                ticketOrderService.placeOrder(req.getParameter("movieTitle"), "abc",
                        Integer.parseInt(req.getParameter("numTickets"))));


        return "orderConfirmation";
    }

}
