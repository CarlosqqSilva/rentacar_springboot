package RentaCarExercise.springboot.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Entity
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
    private int pricePerHour;

    @OneToMany(mappedBy = "car")
    private List<Rental> rental = new ArrayList<>();

    public Car() {
    }

    public Car(String brand, String plate, int km, int horsePower, int pricePerHour) {
        this.brand = brand;
        this.plate = plate;
        this.km = km;
        this.horsePower = horsePower;
        this.pricePerHour = pricePerHour;

    }
}
