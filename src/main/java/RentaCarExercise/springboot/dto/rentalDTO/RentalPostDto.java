package RentaCarExercise.springboot.dto.rentalDTO;

import java.time.LocalDate;

public record RentalPostDto(
        Long ClientID,
        Long CarID,
        LocalDate endOfRental
) {
}
