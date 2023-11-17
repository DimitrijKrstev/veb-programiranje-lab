package mk.finki.ukim.mk.lab.service.impl;

import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.search(text);
    }

    @Override
    public List<Movie> searchMovies(String text, Float rating) {
        return movieRepository.search(text).stream().filter(movie -> movie.getRating() > rating).collect(Collectors.toList());
    }

    @Override
    public void add(String movieTitle, String summary, String rating, String productionId) {
        movieRepository.add(new Movie(movieTitle, summary, Double.parseDouble(rating),
                productionRepository.findById(Long.parseLong(productionId)).orElse(null)));
    }

    @Override
    public Movie findById(Long movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }

    @Override
    public void editMovieById(Long movieId, String title, String summary, String rating, String productionId) {
        Optional<Production> production = productionRepository.findById(Integer.parseInt(productionId));
        Production prod = production.orElse(null);
        movieRepository.findById(movieId).ifPresent(
                m -> {
                    m.setTitle(title);
                    m.setSummary(summary);
                    m.setRating(Double.parseDouble(rating));
                    m.setProduction(prod);
                }
        );
    }

    @Override
    public void deleteById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid movie id"));
        movieRepository.findAll().remove(movie);
    }

}
