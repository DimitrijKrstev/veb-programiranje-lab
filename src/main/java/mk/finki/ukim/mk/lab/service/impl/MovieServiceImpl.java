package mk.finki.ukim.mk.lab.service.impl;

import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.jpa.MovieRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ProductionRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return movieRepository.findAllByTitleContainingIgnoreCase(text);
    }

    @Override
    public List<Movie> searchMovies(String text, Float rating) {
        return movieRepository.findAllByTitleContainingIgnoreCaseAndRating(text, Double.valueOf(rating));
    }

    @Override
    public void add(String movieTitle, String summary, String rating, String productionId) {
        movieRepository.save(Movie.builder()
                .title(movieTitle)
                .summary(summary)
                .rating(Double.parseDouble(rating))
                .production(productionRepository.findById(Long.parseLong(productionId)).orElse(null))
                .build());
    }

    @Override
    public Movie findById(Long movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }

    @Override
    public void editMovieById(Long movieId, String title, String summary, String rating, String productionId) {
        Optional<Production> production = productionRepository.findById(Long.parseLong(productionId));
        Production prod = production.orElse(null);
        movieRepository.findById(movieId).ifPresent(
                m -> {
                    m.setTitle(title);
                    m.setSummary(summary);
                    m.setRating(Double.parseDouble(rating));
                    m.setProduction(prod);
                    movieRepository.save(m);
                }
        );
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

}
