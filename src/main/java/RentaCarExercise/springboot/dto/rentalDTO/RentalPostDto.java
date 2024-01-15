package RentaCarExercise.springboot.dto.rentalDTO;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RentalPostDto(
        Long ClientID,
        Long CarID,
        LocalDate endOfRental
) {
}
