package mk.finki.ukim.mk.lab.model;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
// @Data as this generates equals, hashCode and toString methods, which in the case of jpa entities should be hand generated.
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    private double rating;

    @ManyToOne
    private Production production;
}
