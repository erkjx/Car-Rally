package pl.CarRally.carrally.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.CarRally.carrally.User.UserQueryEntity;

import java.util.List;
import java.util.Optional;

@Repository
public
interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
    void deleteById(Long carId);
    List<Car> findAllByUser(UserQueryEntity user);

    Optional<Car> findByIdAndUser(Long id, UserQueryEntity user);
}