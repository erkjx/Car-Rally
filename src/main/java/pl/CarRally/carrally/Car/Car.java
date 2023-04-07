package pl.CarRally.carrally.Car;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.CarRally.carrally.User.UserQueryEntity;

import javax.persistence.*;


@Entity
@Table(name = "car")
@RequiredArgsConstructor
@Getter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String carmodel;
    private int productionyear;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserQueryEntity user;

    public Car(String brand, String carmodel, int productionyear, UserQueryEntity user) {
        this.brand = brand;
        this.carmodel = carmodel;
        this.productionyear = productionyear;
        this.user = user;
    }
    void update(String brand, String carModel, int productionYear) {
        this.brand = brand;
        this.carmodel = carModel;
        this.productionyear = productionYear;
    }
    void setId(Long id) {
        this.id = id;
    }
}
