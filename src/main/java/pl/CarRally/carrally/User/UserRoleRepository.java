package pl.CarRally.carrally.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByType(UserRoleType type);
    boolean existsByType(UserRoleType type);
}
