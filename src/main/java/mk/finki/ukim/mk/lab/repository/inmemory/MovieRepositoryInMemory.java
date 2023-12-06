package mk.finki.ukim.mk.lab.repository.inmemory;
import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

//@Repository
public class MovieRepositoryInMemory {

    public List<Movie> findAll(){
        return DataHolder.movies;
    }

    public List<Movie> search(String text) {
        return DataHolder.movies
                .stream()
                .filter(c -> c.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                        c.getSummary().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addMovie(Movie movie){
        DataHolder.movies.add(movie);
    }

    public Optional<Movie> findById(Long movieID){
        return DataHolder.movies.stream().filter((Movie m) -> Objects.equals(m.getId(), movieID)).findFirst();
    }

}
