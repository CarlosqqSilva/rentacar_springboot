package RentaCarExercise.springboot.converters;

import RentaCarExercise.springboot.dto.rentalDTO.RentalCreateDto;
import RentaCarExercise.springboot.dto.rentalDTO.RentalPostDto;
import RentaCarExercise.springboot.model.Rental;

public class RentalConverter {

    public static RentalCreateDto modelRentalToRentalDto(Rental rental) {
        return new RentalCreateDto(
                rental.getClient(),
                rental.getCar(),
                rental.getDateOfRental(),
                rental.getEndOfRental(),
                rental.getId()
        );
    }

    public static RentalPostDto modelRentalToRentalPostDto(Rental rental) {
        return new RentalPostDto(
                rental.getClient().getId(),
                rental.getCar().getId(),
                rental.getEndOfRental()
        );
    }

    public static Rental rentalDtoToModelRental(RentalPostDto rentalPostDto) {
        return new Rental(
                rentalPostDto.CarID(),
                rentalPostDto.ClientID(),
                rentalPostDto.endOfRental()
        );
    }
}
