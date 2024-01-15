package RentaCarExercise.springboot.Dto.RentalDTO;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RentalPostDto(
        Long ClientID,
        Long CarID,
        LocalDate endOfRental
) {
}
