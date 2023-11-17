package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.ProductionService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping({"/movies", "/"})
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final TicketOrderService ticketOrderService;
    private final ProductionService productionService;

    @GetMapping
    public String getMoviesPage(HttpServletRequest request,
                                @RequestParam(required = false) String titleInput,
                                @RequestParam(required = false) String ratingInput,
                                Model model){

//        Integer visits = (Integer) request.getServletContext().getAttribute("numberOfVisits");
//        request.getServletContext().setAttribute("numberOfVisits", ++visits);

        List<Movie> movieList =
                (titleInput == null || titleInput.isEmpty()) ? movieService.listAll() :
                        (ratingInput == null || ratingInput.isEmpty()) ? movieService.searchMovies(titleInput) :
                                movieService.searchMovies(titleInput, Float.parseFloat(ratingInput));

        model.addAttribute("movies", movieList);
//        model.addAttribute("visits", visits);

        return "listMovies";
    }

    @GetMapping("/add")
    public String getAddMoviePage(@RequestParam(required = false) String error, Model model){
        //producers
        model.addAttribute("producers", productionService.findAll());
        model.addAttribute("action", "add");

        return "addMovie";
    }


    @PostMapping("/add")
    public String saveMovie(HttpServletRequest request) {
        movieService.add(request.getParameter("movieTitle"),
                request.getParameter("summary"),
                request.getParameter("rating"),
                request.getParameter("productionId"));

        return "redirect:/movies";
    }

    @PostMapping("/edit/{movieId}")
    public String editMovie(@PathVariable Long movieId, HttpServletRequest request, Model model){
        String title = request.getParameter("movieTitle");
        String summary = request.getParameter("summary");
        String rating = request.getParameter("rating");
        String productionId = request.getParameter("productionId");

        movieService.editMovieById(movieId, title, summary, rating, productionId);

        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id){
        movieService.deleteById(id);

        return "redirect:/movies";
    }

    @GetMapping("/edit-form/{movieId}")
    public String getEditMovieForm(@PathVariable Long movieId, Model model){
        Movie movie = movieService.findById(movieId);

        model.addAttribute("movie", movie);
        model.addAttribute("producers", productionService.findAll());
        model.addAttribute("action", "edit/" + movieId);

        return "addMovie";
    }

}
