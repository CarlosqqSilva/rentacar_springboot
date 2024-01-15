package RentaCarExercise.springboot.Dto.CarDTO;

import lombok.Builder;

@Builder
public record CarUpdateDto(
        int km) {
}
