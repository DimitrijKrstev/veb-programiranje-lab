package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.TicketOrder;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
    List<Movie> searchMovies(String text, Float rating);
    void add(String title, String summary, String rating, String productionId);
    void editMovieById(Long movieId, String title, String summary, String rating, String productionId);
    void deleteById(Long id);
    Movie findById(Long movieId);
}
