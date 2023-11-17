package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DataHolder {
    public static List<Movie> movies = null;
    public static List<Production> productions = null;

    @PostConstruct
    public void init() {
        movies = new ArrayList<>();
        productions = new ArrayList<>();

        productions.add(new Production("20th Century Fox", "USA", "str. 1"));
        productions.add(new Production("Paramount Pictures", "USA", "str. 2"));
        productions.add(new Production("Warner Bros", "USA", "str. 3"));
        productions.add(new Production("DreamWorks Pictures", "USA", "str. 4"));
        productions.add(new Production("Lionsgate", "USA", "str. 5"));

        movies.add(new Movie("Inception", "mind-bending thriller", 9.3,
                productions.get((int) (Math.random() * 5))));
        movies.add(new Movie("The Shawshank Redemption", "drama and redemption", 9.2,
                productions.get((int) (Math.random() * 5))));
        movies.add(new Movie("Star Wars: Episode IV", "epic space opera", 8.7,
                productions.get((int) (Math.random() * 5))));
        movies.add(new Movie("The Godfather", "mafia classic", 9.1,
                productions.get((int) (Math.random() * 5))));
        movies.add(new Movie("Jurassic Park", "dinosaur adventure", 8.1,
                productions.get((int) (Math.random() * 5))));
        movies.add(new Movie("The Matrix", "sci-fi action", 8.7,
                productions.get((int) (Math.random() * 5))));
        movies.add(new Movie("The Dark Knight", "superhero masterpiece", 9.0,
                productions.get((int) (Math.random() * 5))));
        movies.add(new Movie("Forrest Gump", "heartwarming drama", 8.8,
                productions.get((int) (Math.random() * 5))));
        movies.add(new Movie("Titanic", "epic romance", 7.8,
                productions.get((int) (Math.random() * 5))));
        movies.add(new Movie("Pulp Fiction", "cult classic", 8.9,
                productions.get((int) (Math.random() * 5))));
    }
}