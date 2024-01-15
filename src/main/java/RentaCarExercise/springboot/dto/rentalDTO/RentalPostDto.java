package RentaCarExercise.springboot.dto.rentalDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record RentalPostDto(

        @Min(value = 1)
        Long CarID,
        @Min(value = 1)
        Long ClientID,
        @Past(message = "Invalid_Date")
        LocalDate endOfRental,
        @Min(value = 0, message = "Invalid_Amount")
        int pricePerDay
) {
}
