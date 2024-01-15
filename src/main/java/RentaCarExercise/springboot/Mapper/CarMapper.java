package RentaCarExercise.springboot.Mapper;

import RentaCarExercise.springboot.Dto.CarDTO.CarCreateDto;
import RentaCarExercise.springboot.Entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    Car carDtoToEntityCar(CarCreateDto carCreateDto);

    CarCreateDto EntityCarToCarDto(Car car);

    List<CarCreateDto> EntityCarToCarDto(List<Car> car);

    List<Car> carDtoToEntityCar(List<CarCreateDto> carCreateDto);
}
