package RentaCarExercise.springboot.Mapper;

import RentaCarExercise.springboot.Dto.RentalDTO.RentalCreateDto;
import RentaCarExercise.springboot.Entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    Rental rentalDtoToEntityRental(RentalCreateDto rentalCreateDto);

    RentalCreateDto EntityRentalToRentalDto(Rental rental);

    List<RentalCreateDto> EntityRentalToRentalDto(List<Rental> rental);

    List<Rental> rentalDtoToEntityRental(List<RentalCreateDto> rentalCreateDto);
}
