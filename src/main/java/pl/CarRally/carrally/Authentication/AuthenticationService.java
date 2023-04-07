package pl.CarRally.carrally.Authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.CarRally.carrally.User.UserDetailsImpl;

import java.util.Set;
import java.util.stream.Collectors;

@Service
class AuthenticationService {

    private final TokenService tokenService;
    private final AuthenticationManager manager;
    AuthenticationService(final TokenService tokenService, final AuthenticationManager manager) {
        this.tokenService = tokenService;
        this.manager = manager;
    }

    AuthenticationResponseDto authenticateUser(AuthenticationRequestDto authenticationRequestDto) {
        Authentication authentication = authenticate(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = tokenService.createToken(userDetails);
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        return new AuthenticationResponseDto(
                token,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    private Authentication authenticate(String username, String password){
        try{
            return manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (BadCredentialsException exception) {
            throw new IncorrectLoginDataException("Incorrect username or password.");
        }
    }
}
