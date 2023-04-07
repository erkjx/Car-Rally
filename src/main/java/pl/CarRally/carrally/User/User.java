package pl.CarRally.carrally.User;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> userRoles = new HashSet<>();

    User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
    Set<UserRole> getRoles() {
        return userRoles;
    }
    void addRole(UserRole role){
        if(userRoles.contains(role)){
            return;
        }
        userRoles.add(role);
    }

    void removeRole(UserRole role) {
        userRoles.remove(role);
    }

    void updateUserByRequest(UserRegisterRequest userRegisterRequest) {
        this.email = userRegisterRequest.getEmail();
        this.username = userRegisterRequest.getUsername();
        this.password = userRegisterRequest.getPassword();
    }
}
