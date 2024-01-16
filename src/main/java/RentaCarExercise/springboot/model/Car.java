package RentaCarExercise.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Rental> rental = new ArrayList<>();

    public Car(String brand, String plate, int km, int horsePower, int pricePerDay) {
        this.brand = brand;
        this.plate = plate;
        this.km = km;
        this.horsePower = horsePower;
        this.pricePerDay = pricePerDay;

    }
}
