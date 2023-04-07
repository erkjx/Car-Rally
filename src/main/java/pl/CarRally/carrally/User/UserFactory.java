package pl.CarRally.carrally.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    private final PasswordEncoder passwordEncoder;
    UserFactory(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    User fromUserRegisterDto(UserRegisterRequest userRegisterRequest) {
        return new User(userRegisterRequest.getEmail(), userRegisterRequest.getUsername(), passwordEncoder.encode(userRegisterRequest.getPassword()));
    }
}
