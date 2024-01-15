package RentaCarExercise.springboot.mapper;

import RentaCarExercise.springboot.dto.rentalDTO.RentalCreateDto;
import RentaCarExercise.springboot.model.Rental;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    Rental rentalDtoToModelRental(RentalCreateDto rentalCreateDto);

    RentalCreateDto modelRentalToRentalDto(Rental rental);

    List<Rental> rentalDtoToModelRental(List<RentalCreateDto> rentalCreateDto);

    List<RentalCreateDto> modelRentalToRentalDto(List<Rental> rental);

}
