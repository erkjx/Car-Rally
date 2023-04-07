package pl.CarRally.carrally.Car;

class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long carId) {
        super("Car with %s id not found".formatted(carId));
    }
}
