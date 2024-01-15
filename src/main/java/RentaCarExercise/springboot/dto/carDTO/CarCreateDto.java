package RentaCarExercise.springboot.dto.carDTO;

import jakarta.validation.constraints.NotBlank;

public record CarCreateDto(
        @NotBlank(message = "Must have a brand")
        String brand,
        @NotBlank(message = "Must have a plate")
        String plate,
        int km,
        int horsePower,
        int pricePerDay
) {
}
