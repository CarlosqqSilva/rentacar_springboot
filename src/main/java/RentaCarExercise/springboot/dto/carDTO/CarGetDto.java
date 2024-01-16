package RentaCarExercise.springboot.dto.carDTO;

public record CarGetDto(
        long id,
        String brand,
        String plate,
        int km,
        int pricePerDay,
        int horsePower
) {
}
