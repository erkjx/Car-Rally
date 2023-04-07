package pl.CarRally.carrally.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
class TokenService {

    private final JwtConfigurationProperties properties;

    TokenService(JwtConfigurationProperties properties) {
        this.properties = properties;
    }

    public String createToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpirationTimeMs()))
                .signWith(SignatureAlgorithm.HS512, properties.getSecret())
                .compact();
    }
    String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    boolean isValidForUser(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return !isTokenExpired(token) && username.equals(userDetails.getUsername());
    }

    private Boolean isTokenExpired(String token) {
        return getAllClaimsFromToken(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}