package pl.CarRally.carrally.Authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.CarRally.carrally.User.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

class AuthenticationFilter extends OncePerRequestFilter {

    private final String BEARER = "Bearer ";
    private final TokenService tokenService;
    private final UserDetailsServiceImpl userDetailsService;
    public AuthenticationFilter(TokenService tokenService, UserDetailsServiceImpl userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional.ofNullable(
                request.getHeader("Authorization")
        ).filter(authHeader -> authHeader.startsWith(BEARER))
                .map(authHeader -> authHeader.substring(BEARER.length()))
                .ifPresent(token -> {
                    String username = tokenService.getUsernameFromToken(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if(tokenService.isValidForUser(token, userDetails)){
                        var authentication = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                });
        filterChain.doFilter(request, response);
    }
}
