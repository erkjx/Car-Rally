package pl.CarRally.carrally.Car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarDto {

    private String brand;
    private String carmodel;
    private int productionyear;
}