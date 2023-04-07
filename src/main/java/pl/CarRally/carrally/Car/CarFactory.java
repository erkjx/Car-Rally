package pl.CarRally.carrally.Car;

import org.springframework.stereotype.Service;
import pl.CarRally.carrally.User.UserQueryEntity;


@Service
class CarFactory {

     Car fromDto(CarDto carDto, UserQueryEntity userQueryEntity) {
        return new Car(
                carDto.getBrand(),
                carDto.getCarmodel(),
                carDto.getProductionyear(),
                userQueryEntity);
    }
    public CarQueryEntity createCarQueryEntityById(Long carId, String brand)  {
        return new CarQueryEntity(carId, brand);
    }
}