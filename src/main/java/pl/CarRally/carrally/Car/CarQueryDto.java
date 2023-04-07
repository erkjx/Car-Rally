package pl.CarRally.carrally.Car;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarQueryDto {

    private Long id;
    private String brand;
    private String carmodel;
    private int productionyear;
}
