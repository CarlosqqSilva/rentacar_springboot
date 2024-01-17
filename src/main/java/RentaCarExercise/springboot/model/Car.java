package RentaCarExercise.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String brand;

    @Column(unique = true)
    private String plate;

    @Column(nullable = false)
    private int km;

    @Column(nullable = false)
    private int horsePower;

    @Column(nullable = false)
    private int pricePerDay;


    public Car(String brand, String plate, int km, int horsePower, int pricePerDay) {
        this.brand = brand;
        this.plate = plate;
        this.km = km;
        this.horsePower = horsePower;
        this.pricePerDay = pricePerDay;

    }
}
