package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(urlPatterns = "")
@AllArgsConstructor
public class MovieListServlet extends HttpServlet{
    private final MovieService movieService;
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context =  new WebContext(webExchange);

        String title = req.getParameter("titleInput");
        String rating = req.getParameter("ratingInput");
        context.setVariable("movies",
                (title == null) ? movieService.listAll() :
                        (rating == null) ? movieService.searchMovies(title) :
                            movieService.searchMovies(title, Float.parseFloat(rating)));

        springTemplateEngine.process(
                "listMovies.html",
                context,
                resp.getWriter()
        );
    }
}
