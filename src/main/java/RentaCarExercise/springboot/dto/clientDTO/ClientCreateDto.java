package RentaCarExercise.springboot.dto.clientDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientCreateDto(

        @NotBlank(message = "Must have a name")
        String name,
        @Email(message = "Must insert an email")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email")
        String email,
        int drivingLicense,
        int nif
) {
}
