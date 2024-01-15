package RentaCarExercise.springboot.dto.carDTO;

import lombok.Builder;

@Builder
public record CarUpdateKmDto(
        int km) {
}
