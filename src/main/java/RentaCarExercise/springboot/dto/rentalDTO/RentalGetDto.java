package RentaCarExercise.springboot.dto.rentalDTO;

import java.time.LocalDate;

public record RentalGetDto(
        long id,
        long client,
        long car,
        LocalDate dateOfRental,
        LocalDate endOfRental
) {
}
