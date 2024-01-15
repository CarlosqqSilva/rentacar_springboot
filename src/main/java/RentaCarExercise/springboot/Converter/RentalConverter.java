package RentaCarExercise.springboot.Converter;

import RentaCarExercise.springboot.Dto.RentalDTO.RentalCreateDto;
import RentaCarExercise.springboot.Dto.RentalDTO.RentalPostDto;
import RentaCarExercise.springboot.Entity.Rental;

public class RentalConverter {

    public static RentalCreateDto fromEntityRentalToDto(Rental rental) {
        return new RentalCreateDto(
                rental.getClient(),
                rental.getCar(),
                rental.getDateOfRental(),
                rental.getEndOfRental(),
                rental.getId()
        );
    }

    public static RentalPostDto fromEntityToDtoCreate(Rental rental) {
        return new RentalPostDto(
                rental.getClient().getId(),
                rental.getCar().getId(),
                rental.getEndOfRental()
        );
    }

    public static Rental fromDtoToEntityRental(RentalCreateDto rental) {
        return new Rental(
                rental.client(),
                rental.car(),
                rental.endOfRental()
        );
    }

}
