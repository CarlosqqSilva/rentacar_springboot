package RentaCarExercise.springboot.dto.carDTO;

import lombok.Builder;

@Builder
public record CarUpdateDto(
        int km) {
}
