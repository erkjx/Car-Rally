package pl.CarRally.carrally.User;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.CarRally.carrally.Car.CarQueryDto;
import pl.CarRally.carrally.Car.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
class UserController {

    private final UserQueryRepository userQueryRepository;
    private final UserService userService;
    private final UserAuthenticationService authenticationUserService;
    private final CarService carService;

    UserController(final UserQueryRepository userQueryRepository, final UserService userService, final UserAuthenticationService authenticationUserService,
                   CarService carService) {
        this.userQueryRepository = userQueryRepository;
        this.userService = userService;
        this.authenticationUserService = authenticationUserService;
        this.carService = carService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/me/info")
    ResponseEntity<UserQueryDto> getInfoAboutMe() {
        var user = authenticationUserService.getLoggedUser();
        return ResponseEntity.ok(userQueryRepository.findByUsername(user.getUsername()).get());
    }
    @GetMapping("/{username}/cars")
    ResponseEntity<List<CarQueryDto>> getCarsByUsername(@PathVariable String username){
        var user = userService.getQueryUserByUsername(username);
        return ResponseEntity.ok(carService.findAllCarDtoByUser(user));
    }

    @GetMapping("/{username}/info")
    ResponseEntity<UserQueryDto> getInfoAboutUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userQueryRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username)));
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> addUser(@RequestBody UserRegisterRequest registerUserRequest) {
        userService.createUser(registerUserRequest);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody UserRegisterRequest registerUserRequest) {
        userService.updateUserByUsername(username, registerUserRequest);
        return ResponseEntity.status(204).build();
    }

    @PutMapping()
    ResponseEntity<?> updateLoggedUser(@RequestBody UserRegisterRequest registerUserRequest) {
        userService.updateLoggedUser(registerUserRequest);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{username}/give/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> giveAdminRoleToUser(@PathVariable String username) {
        userService.giveAdminRoleToUser(username);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{username}/take/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> takeAdminRoleFromUser(@PathVariable String username) {
        userService.takeAdminRoleFromUser(username);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/change/password")
    ResponseEntity<?> changePasswordByLoggedUser(@RequestBody UserPasswordRequest userPasswordRequest) {
        userService.changePassword(userPasswordRequest);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/change/email")
    ResponseEntity<?> changeEmailByLoggedUser(@RequestBody UserEmailRequest userEmailRequest) {
        userService.changeEmail(userEmailRequest);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping()
    ResponseEntity<?> deleteLoggedUser() {
        userService.deleteLoggedUser();
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.status(204).build();
    }
}