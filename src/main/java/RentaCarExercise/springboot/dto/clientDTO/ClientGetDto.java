package RentaCarExercise.springboot.dto.clientDTO;

public record ClientGetDto(
        String name,
        String email,
        int nif,
        long id
) {
}
