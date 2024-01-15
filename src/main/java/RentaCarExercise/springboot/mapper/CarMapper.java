package RentaCarExercise.springboot.mapper;

import RentaCarExercise.springboot.dto.carDTO.CarCreateDto;
import RentaCarExercise.springboot.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    Car carDtoToEntityCar(CarCreateDto carCreateDto);

    CarCreateDto EntityCarToCarDto(Car car);

    List<CarCreateDto> EntityCarToCarDto(List<Car> car);

    List<Car> carDtoToEntityCar(List<CarCreateDto> carCreateDto);
}
