package RentaCarExercise.springboot.repositories;

import RentaCarExercise.springboot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {


    List<Client> findByEmail(String email);

    Optional<Client> findClientByEmail(String email);

    Optional<Client> findByNif(int nif);
}
