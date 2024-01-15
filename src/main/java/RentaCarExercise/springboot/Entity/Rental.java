package RentaCarExercise.springboot.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate dateOfRental;

    private LocalDate endOfRental;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Car car;

    public Rental() {
    }

    public Rental(Client client, Car car, LocalDate endOfRental) {
        this.client = client;
        this.car = car;
        this.endOfRental = endOfRental;
        this.dateOfRental = LocalDate.now();
    }

}
