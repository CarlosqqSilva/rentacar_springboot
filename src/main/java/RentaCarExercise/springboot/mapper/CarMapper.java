package RentaCarExercise.springboot.mapper;

import RentaCarExercise.springboot.dto.carDTO.CarCreateDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdateKmDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdatePriceDto;
import RentaCarExercise.springboot.model.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car carDtoToModelCar(CarCreateDto carCreateDto);

    CarCreateDto modelCarToCarDto(Car car);

    List<CarCreateDto> modelCarToCarDto(List<Car> car);

    List<Car> carDtoToModelCar(List<CarCreateDto> carCreateDto);

    CarUpdateKmDto modelCarUpdateKmToDto(Car car);

    CarUpdatePriceDto modelCarUpdatePriceDto(Car car);
}
