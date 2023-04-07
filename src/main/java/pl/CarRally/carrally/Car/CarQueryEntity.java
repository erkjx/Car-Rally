package pl.CarRally.carrally.Car;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarQueryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String brand;
}