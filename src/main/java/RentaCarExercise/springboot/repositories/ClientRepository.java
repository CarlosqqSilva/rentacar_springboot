package RentaCarExercise.springboot.repositories;

import RentaCarExercise.springboot.model.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {


    List<Client> findByEmail(String email);

    Optional<Client> findClientByEmail(String email);

    Optional<Client> findByNif(int nif);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE clients AUTO_INCREMENT = 1", nativeQuery = true)
    void resetId();
}
