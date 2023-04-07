package pl.CarRally.carrally.Car;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CarExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleCarNotFoundException(CarNotFoundException exception){
        return exception.getMessage();
    }

}
