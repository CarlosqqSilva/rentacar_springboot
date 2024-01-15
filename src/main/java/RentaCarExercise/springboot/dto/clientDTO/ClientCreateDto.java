package RentaCarExercise.springboot.dto.clientDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ClientCreateDto(

        @NotBlank(message = "Must have a name")
        String name,
        @Email(message = "Must insert an email")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email")
        String email,
        @Past(message = "Insert a valid date of birth")
        LocalDate dateOfBirth,
        Long id,
        int drivingLicense,
        int nif
) {
}
