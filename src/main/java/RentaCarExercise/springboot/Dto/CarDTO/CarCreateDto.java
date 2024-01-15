package RentaCarExercise.springboot.Dto.CarDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CarCreateDto(
        @NotBlank(message = "Must have a brand")
        String brand,
        @NotBlank(message = "Must have a plate")
        String plate,
        int km,
        int horsePower,
        int pricePerHour
) {
}
