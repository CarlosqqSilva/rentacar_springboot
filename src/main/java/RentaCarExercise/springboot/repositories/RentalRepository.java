package RentaCarExercise.springboot.repositories;

import RentaCarExercise.springboot.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {


}
