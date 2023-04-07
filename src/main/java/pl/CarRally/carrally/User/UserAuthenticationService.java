package pl.CarRally.carrally.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    private final UserRepository userRepository;

    public UserAuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserQueryEntity getLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails =  (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        var user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
        return new UserQueryEntity(user.getId(), user.getUsername());
    }
}
