package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticketOrder")
@AllArgsConstructor
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;

    @GetMapping
    public String displayOrder(HttpServletRequest req, Model model){
        model.addAttribute("orders", ticketOrderService.getTicketOrders());

        return "orderConfirmation";
    }

    @PostMapping("{movieTitle}")
    public String changeRating(@PathVariable String movieTitle, HttpServletRequest req){
        String rating = req.getParameter("rating");

        Movie movie = movieService.searchMovies(movieTitle).get(0);
        movieService.editMovieById(movie.getId(), movieTitle, movie.getSummary(), String.valueOf((movie.getRating() +
                Double.parseDouble(rating)) / 2), String.valueOf(movie.getProduction().getId()));

        return "redirect:/movies";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(HttpServletRequest req, Model model){

        if(req.getParameter("movieTitle") != null) model.addAttribute("order", ticketOrderService
                .placeOrder(req.getParameter("movieTitle"), "abc", req.getRemoteAddr(),
                        Integer.parseInt(req.getParameter("numTickets"))));

        return "redirect:/ticketOrder";
    }

}
