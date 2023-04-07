package pl.CarRally.carrally.Car;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.CarRally.carrally.User.UserAuthenticationService;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
class CarController {

    private final CarService carService;
    private final UserAuthenticationService userAuthenticationService;
    private final CarFactory carFactory;

    public CarController(
            final CarService carService,
            final UserAuthenticationService userAuthenticationService, CarFactory carFactory) {
        this.carService = carService;
        this.userAuthenticationService = userAuthenticationService;
        this.carFactory = carFactory;
    }

    @GetMapping
    ResponseEntity<List<CarQueryDto>> getCars() {
        return ResponseEntity.ok(carService.findAllCarDtoByUser(userAuthenticationService.getLoggedUser()));
    }

    @GetMapping("/{id}")
    ResponseEntity<CarQueryDto> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(
                carService.findDtoByIdAndUser(
                        id,
                        userAuthenticationService.getLoggedUser()).orElseThrow(() -> new CarNotFoundException(id)));
    }

    @PostMapping
    ResponseEntity<Long> createCar(@RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.addCar(carDto));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        carService.updateCar(id, carDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

}