package RentaCarExercise.springboot.dto.rentalDTO;

import RentaCarExercise.springboot.model.Car;
import RentaCarExercise.springboot.model.Client;

import java.time.LocalDate;

public record RentalCreateDto(
        Client client,
        Car car,
        LocalDate dateOfRental,
        LocalDate endOfRental,
        long id
) {
}
