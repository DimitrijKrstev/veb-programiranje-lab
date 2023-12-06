package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="productions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String address;
}
