package RentaCarExercise.springboot.repositories;

import RentaCarExercise.springboot.model.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByPlate(String plate);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE cars AUTO_INCREMENT = 1", nativeQuery = true)
    void resetId();
}
