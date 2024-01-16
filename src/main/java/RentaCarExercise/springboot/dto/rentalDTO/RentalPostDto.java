package RentaCarExercise.springboot.dto.rentalDTO;

import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public record RentalPostDto(

        @Min(value = 1)
        Long CarID,
        @Min(value = 1)
        Long ClientID,
        LocalDate endOfRental
) {
}
