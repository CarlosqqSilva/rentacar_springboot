package RentaCarExercise.springboot.Services.CarService;

import RentaCarExercise.springboot.Dto.CarDTO.CarCreateDto;
import RentaCarExercise.springboot.Dto.CarDTO.CarUpdateDto;
import RentaCarExercise.springboot.Entity.Car;
import RentaCarExercise.springboot.Expections.CarExceptions.AddCarException;
import RentaCarExercise.springboot.Expections.CarExceptions.DeleteCarException;
import RentaCarExercise.springboot.Expections.CarExceptions.GetByIdException;
import RentaCarExercise.springboot.Expections.CarExceptions.UpdateCarException;

import java.util.List;

public interface CarService {
    List<CarCreateDto> getCars();

    void addCar(CarCreateDto car) throws AddCarException;

    CarUpdateDto updateCar(Long id, CarUpdateDto carDto) throws UpdateCarException;

    void deleteCar(Long id) throws DeleteCarException;

    Car getById(Long id) throws GetByIdException;
}
