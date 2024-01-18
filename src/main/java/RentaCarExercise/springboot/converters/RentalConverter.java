package RentaCarExercise.springboot.converters;

import RentaCarExercise.springboot.dto.rentalDTO.RentalCreateDto;
import RentaCarExercise.springboot.dto.rentalDTO.RentalGetDto;
import RentaCarExercise.springboot.dto.rentalDTO.RentalPostDto;
import RentaCarExercise.springboot.model.Rental;

import java.util.List;

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

    public static RentalGetDto modelRentalToRentalGetDto(Rental rental) {
        return new RentalGetDto(
                rental.getId(),
                rental.getClient().getId(),
                rental.getCar().getId(),
                rental.getDateOfRental(),
                rental.getEndOfRental()
        );
    }

    public static List<RentalGetDto> fromClientModelListToGetDtoList(List<Rental> rentals) {
        return rentals.stream()
                .map(RentalConverter::modelRentalToRentalGetDto)
                .toList();
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
