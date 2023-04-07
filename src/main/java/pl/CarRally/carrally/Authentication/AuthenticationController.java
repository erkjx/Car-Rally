package pl.CarRally.carrally.Authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.CarRally.carrally.User.UserEmailRequest;
import pl.CarRally.carrally.User.UserRegisterRequest;
import pl.CarRally.carrally.User.UserService;
import pl.CarRally.carrally.User.UsernameRequest;


@RestController
@RequestMapping("/api/auth")
class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    AuthenticationController(final AuthenticationService authenticationService, final UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    void registerUser(@RequestBody UserRegisterRequest userRegisterRequest){
        userService.createUser(userRegisterRequest);
    }

    @PostMapping("/login")
    ResponseEntity<AuthenticationResponseDto> loginUser(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        return ResponseEntity.ok(authenticationService.authenticateUser(authenticationRequestDto));
    }

    @GetMapping("/check/username")
    ResponseEntity<Boolean> isUsernameAvailable(@RequestBody UsernameRequest usernameRequest) {
        return ResponseEntity.ok(userService.isUsernameAvailable(usernameRequest.getUsername()));
    }

    @GetMapping("/check/email")
    ResponseEntity<Boolean> isEmailAvailable(@RequestBody UserEmailRequest userEmailRequest) {
        return ResponseEntity.ok(userService.isEmailAvailable(userEmailRequest.getEmail()));
    }
}
