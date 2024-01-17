package RentaCarExercise.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private int drivingLicense;

    @Column(nullable = false, unique = true)
    private int nif;

    public Client(String name, String email, int drivingLicense, int nif) {
        this.name = name;
        this.email = email;
        this.drivingLicense = drivingLicense;
        this.nif = nif;
    }
}
