package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByTitleContainingIgnoreCase(String text);

    List<Movie> findAllByRating(Double rating);

    List<Movie> findAllByTitleContainingIgnoreCaseAndRating(String text, Double rating);

}