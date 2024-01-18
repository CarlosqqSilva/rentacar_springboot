package RentaCarExercise.springboot.converters;

import RentaCarExercise.springboot.dto.carDTO.CarCreateDto;
import RentaCarExercise.springboot.dto.carDTO.CarGetDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdateKmDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdatePriceDto;
import RentaCarExercise.springboot.model.Car;

public class CarConverter {

    public static CarCreateDto modelCarToCarDto(Car car) {
        return new CarCreateDto(
                car.getBrand(),
                car.getPlate(),
                car.getKm(),
                car.getHorsePower(),
                car.getPricePerDay()
        );
    }

    public static CarUpdateKmDto modelCarUpdateKmToDto(Car car) {
        return new CarUpdateKmDto(
                car.getKm()
        );
    }

    public static CarUpdatePriceDto modelCarUpdatePriceToDto(Car car) {
        return new CarUpdatePriceDto(
                car.getPricePerDay()
        );
    }

    public static Car carDtoToModelCar(CarCreateDto carCreateDto) {
        return new Car(
                carCreateDto.brand(),
                carCreateDto.plate(),
                carCreateDto.km(),
                carCreateDto.horsePower(),
                carCreateDto.pricePerDay()
        );
    }

    public static CarGetDto modelCarToCarGetDto(Car car) {
        return new CarGetDto(
                car.getId(),
                car.getBrand(),
                car.getPlate(),
                car.getKm(),
                car.getPricePerDay(),
                car.getHorsePower()
        );
    }
}
