package pl.CarRally.carrally.Car;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.CarRally.carrally.User.UserAuthenticationService;
import pl.CarRally.carrally.User.UserQueryEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final UserAuthenticationService userAuthenticationService;
    private final CarFactory carFactory;

    public CarService(final CarRepository carRepository, final UserAuthenticationService userAuthenticationService, final CarFactory carFactory) {
        this.carRepository = carRepository;
        this.userAuthenticationService = userAuthenticationService;
        this.carFactory = carFactory;
    }

    public Long addCar(CarDto carDto) {
        var user = userAuthenticationService.getLoggedUser();
        var toSave = carFactory.fromDto(carDto, user);
        return carRepository.save(toSave).getId();
    }

    @Transactional
    public void updateCar(Long carId, CarDto carDto) {
        var car = getCarById(carId);
        car.update(carDto.getBrand(), carDto.getCarmodel(), carDto.getProductionyear());
    }

    public CarQueryEntity getCarQueryEntityById(Long carId) {
        var car = getCarById(carId);
        return carFactory.createCarQueryEntityById(car.getId(), car.getBrand());
    }

    @Transactional

    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }


    private Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    }

    public Optional<CarQueryDto> findDtoByIdAndUser(Long id, UserQueryEntity user){
        Optional<Car> found = carRepository.findByIdAndUser(id, user);
        return found.map(car -> new CarQueryDto(car.getId(), car.getBrand(),car.getCarmodel(), car.getProductionyear()));
    }

    public List<CarQueryDto> findAllCarDtoByUser(UserQueryEntity user){
        return carRepository.findAllByUser(user).stream().map(car -> new CarQueryDto(car.getId(), car.getBrand(),car.getCarmodel(), car.getProductionyear())).collect(Collectors.toList());
    }
}