package RentaCarExercise.springboot.repositories;

import RentaCarExercise.springboot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {


    List<Client> findByEmail(String email);

    @Query("SELECT name FROM Client")
    List<String> getClientNames();

    List<Client> findByName(String name);

    Optional<Client> findClientByEmail(String email);
}
