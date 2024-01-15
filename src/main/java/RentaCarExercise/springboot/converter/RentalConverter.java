package RentaCarExercise.springboot.converter;

import RentaCarExercise.springboot.dto.rentalDTO.RentalCreateDto;
import RentaCarExercise.springboot.dto.rentalDTO.RentalPostDto;
import RentaCarExercise.springboot.model.Rental;

public class RentalConverter {

    public static RentalCreateDto fromModelRentalToDto(Rental rental) {
        return new RentalCreateDto(
                rental.getClient(),
                rental.getCar(),
                rental.getDateOfRental(),
                rental.getEndOfRental(),
                rental.getId()
        );
    }

    public static RentalPostDto fromModelToDtoCreate(Rental rental) {
        return new RentalPostDto(
                rental.getClient().getId(),
                rental.getCar().getId(),
                rental.getEndOfRental()
        );
    }

    public static Rental fromDtoToModelRental(RentalCreateDto rental) {
        return new Rental(
                rental.client(),
                rental.car(),
                rental.endOfRental()
        );
    }

}
