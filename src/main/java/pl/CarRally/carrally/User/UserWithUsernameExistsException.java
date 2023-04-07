package pl.CarRally.carrally.User;

class UserWithUsernameExistsException extends RuntimeException {
    UserWithUsernameExistsException(String username) {
        super("User with username: %s exists".formatted(username));
    }
}
