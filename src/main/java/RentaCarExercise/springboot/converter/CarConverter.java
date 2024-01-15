package RentaCarExercise.springboot.converter;

import RentaCarExercise.springboot.dto.carDTO.CarCreateDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdateKmDto;
import RentaCarExercise.springboot.model.Car;

public class CarConverter {

    public static CarCreateDto fromModelCarToDto(Car car) {
        return CarCreateDto.builder()
                .brand(car.getBrand())
                .plate(car.getPlate())
                .horsePower(car.getHorsePower())
                .km(car.getKm())
                .build();

    }

    public static CarUpdateKmDto fromModelCarUpdateToDto(Car car) {
        return CarUpdateKmDto.builder()
                .km(car.getKm())
                .build();

    }

    public static Car fromDtoToModelCar(CarCreateDto carDto) {
        return new Car(
                carDto.brand(),
                carDto.plate(),
                carDto.horsePower(),
                carDto.km(),
                carDto.pricePerDay()
        );
    }
}
