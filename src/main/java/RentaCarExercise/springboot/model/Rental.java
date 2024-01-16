package RentaCarExercise.springboot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate dateOfRental;

    private LocalDate endOfRental;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonManagedReference
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonManagedReference
    private Car car;


    public Rental(Client client, Car car, LocalDate endOfRental) {
        this.client = client;
        this.car = car;
        this.endOfRental = endOfRental;
        this.dateOfRental = LocalDate.now();
    }
}
