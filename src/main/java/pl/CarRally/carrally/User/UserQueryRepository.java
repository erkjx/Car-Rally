package pl.CarRally.carrally.User;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserQueryRepository extends Repository<User, Long> {
    //Optional<UserQueryDto> findById(Long id);
    Optional<UserQueryDto> findByUsername(String username);
}
