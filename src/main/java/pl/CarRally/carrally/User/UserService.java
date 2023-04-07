package pl.CarRally.carrally.User;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserAuthenticationService authenticationUserService;
    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private final UserFactory userFactory;
    private final PasswordEncoder passwordEncoder;

    public UserService(final UserAuthenticationService authenticationUserService, final UserRepository userRepository, UserRoleRepository roleRepository, final UserFactory userFactory, final PasswordEncoder passwordEncoder) {
        this.authenticationUserService = authenticationUserService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userFactory = userFactory;
        this.passwordEncoder = passwordEncoder;
    }

    public Long createUser(UserRegisterRequest userRegisterRequest) {
        if(userRepository.existsByUsername(userRegisterRequest.getUsername())){
            throw new UserWithUsernameExistsException(userRegisterRequest.getUsername());
        }
        if(userRepository.existsByEmail(userRegisterRequest.getEmail())){
            throw new UserWithEmailExistsException(userRegisterRequest.getEmail());
        }
        User user = userFactory.fromUserRegisterDto(userRegisterRequest);
        user.addRole(roleRepository.findByType(UserRoleType.USER));
        return userRepository.save(user).getId();
    }

    @Transactional
    public void deleteUser(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserQueryEntity getQueryUserByUsername(String username){
        var user = getUserByUsername(username);
        return new UserQueryEntity(user.getId(), user.getUsername());
    }
    public boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    @Transactional
    public void updateLoggedUser(UserRegisterRequest userRegisterRequest){
        var user = getLoggedUser();
        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        if(isEmailAvailable(userRegisterRequest.getEmail()) && isUsernameAvailable(userRegisterRequest.getUsername())){
            user.updateUserByRequest(userRegisterRequest);
        }
    }

    @Transactional
    public void updateUserByUsername(String username, UserRegisterRequest userRegisterRequest) {
        var user = getUserByUsername(username);
        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.updateUserByRequest(userRegisterRequest);
    }

    @Transactional
    public void giveAdminRoleToUser(String username) {
        User user = getUserByUsername(username);
        UserRole admin = roleRepository.findByType(UserRoleType.ADMIN);
        user.addRole(admin);
    }

    @Transactional
    public void takeAdminRoleFromUser(String username) {
        User user = getUserByUsername(username);
        UserRole admin = roleRepository.findByType(UserRoleType.ADMIN);
        user.removeRole(admin);
    }

    @Transactional
    public void changePassword(UserPasswordRequest password) {
        var user = getLoggedUser();
        user.setPassword(passwordEncoder.encode(password.getPassword()));
    }

    @Transactional
    public void changeEmail(UserEmailRequest email) {
        var user = getLoggedUser();
        user.setEmail(email.getEmail());
    }

    @Transactional
    public void deleteLoggedUser() {
        userRepository.deleteByUsername(getLoggedUser().getUsername());
    }

    @Transactional
    public void deleteUserByUsername(String username) {
        if(userRepository.existsByUsername(username)){
            userRepository.deleteByUsername(username);
        }
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow( ()-> new UsernameNotFoundException(username));
    }

    private User getLoggedUser(){
        var queryUser = authenticationUserService.getLoggedUser();
        return userRepository.findByUsername(queryUser.getUsername()).get();
    }
}
