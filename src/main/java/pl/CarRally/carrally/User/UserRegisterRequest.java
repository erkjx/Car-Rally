package pl.CarRally.carrally.User;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRequest {

    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String password;
}