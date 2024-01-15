package RentaCarExercise.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    private List<Rental> rental = new ArrayList<>();

    public Client(String name, String email, int drivingLicense, LocalDate dateOfBirth, int nif) {
        this.name = name;
        this.email = email;
        this.drivingLicense = drivingLicense;
        this.dateOfBirth = dateOfBirth;
        this.nif = nif;
        this.id = getId();
    }

}
