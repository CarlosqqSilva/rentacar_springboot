package RentaCarExercise.springboot.services.carService;

import RentaCarExercise.springboot.dto.carDTO.CarCreateDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdateDto;
import RentaCarExercise.springboot.model.Car;
import RentaCarExercise.springboot.expections.carExceptions.AddCarException;
import RentaCarExercise.springboot.expections.carExceptions.DeleteCarException;
import RentaCarExercise.springboot.expections.carExceptions.GetByIdException;
import RentaCarExercise.springboot.expections.carExceptions.UpdateCarException;

import java.util.List;

public interface CarService {
    List<CarCreateDto> getCars();

    void addCar(CarCreateDto car) throws AddCarException;

    CarUpdateDto updateCar(Long id, CarUpdateDto carDto) throws UpdateCarException;

    void deleteCar(Long id) throws DeleteCarException;

    Car getById(Long id) throws GetByIdException;
}
