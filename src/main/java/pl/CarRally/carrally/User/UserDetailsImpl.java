package pl.CarRally.carrally.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String username;
    private final String email;
    private final String password;
    private final Set<GrantedAuthority> authorities;
    UserDetailsImpl(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = authoritiesFromRoles(user.getRoles());
        this.email = user.getEmail();
    }

    private static Set<GrantedAuthority> authoritiesFromRoles(Set<UserRole> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getType().getName()))
                .collect(Collectors.toSet());
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
