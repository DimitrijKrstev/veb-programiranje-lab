package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="movie_users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Scope("session")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = UserFullNameConverter.class)
    private UserFullName fullname;
    private String username;
    private String password;
    private String role;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<ShoppingCart> carts;
}
