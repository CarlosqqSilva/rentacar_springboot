package RentaCarExercise.springboot.Converter;

import RentaCarExercise.springboot.Dto.CarDTO.CarCreateDto;
import RentaCarExercise.springboot.Dto.CarDTO.CarUpdateDto;
import RentaCarExercise.springboot.Entity.Car;

public class CarConverter {

    public static CarCreateDto fromEntityCarToDto(Car car) {
        return CarCreateDto.builder()
                .brand(car.getBrand())
                .plate(car.getPlate())
                .horsePower(car.getHorsePower())
                .km(car.getKm())
                .build();

    }

    public static CarUpdateDto fromEntityCarUpdateToDto(Car car) {
        return CarUpdateDto.builder()
                .km(car.getKm())
                .build();

    }

    public static Car fromDtoToEntityCar(CarCreateDto carDto) {
        return new Car(
                carDto.brand(),
                carDto.plate(),
                carDto.horsePower(),
                carDto.km()
        );
    }
}
