package pl.CarRally.carrally.Authentication;

import java.util.Set;

public class AuthenticationResponseDto {
    private String token;
    private Long id;
    private String username;
    private String email;
    private Set<String> roles;
    private final String type = "Bearer";

    public AuthenticationResponseDto(String token, Long id, String username, String email, Set<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
