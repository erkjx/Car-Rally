package pl.CarRally.carrally.Authentication;

class IncorrectLoginDataException extends RuntimeException {
    IncorrectLoginDataException(String message) {
        super(message);
    }
}
