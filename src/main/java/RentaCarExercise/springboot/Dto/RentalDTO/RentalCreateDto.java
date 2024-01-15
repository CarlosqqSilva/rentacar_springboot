package RentaCarExercise.springboot.Dto.RentalDTO;

import RentaCarExercise.springboot.Entity.Car;
import RentaCarExercise.springboot.Entity.Client;

import java.time.LocalDate;

public record RentalCreateDto(
        Client client,
        Car car,
        LocalDate dateOfRental,
        LocalDate endOfRental,
        long id
) {
}
