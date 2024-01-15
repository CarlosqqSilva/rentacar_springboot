package RentaCarExercise.springboot.Repositories;

import RentaCarExercise.springboot.Entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {


}
