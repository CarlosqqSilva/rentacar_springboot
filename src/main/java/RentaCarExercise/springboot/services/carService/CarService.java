package RentaCarExercise.springboot.services.carService;

import RentaCarExercise.springboot.dto.carDTO.CarCreateDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdateKmDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdatePriceDto;
import RentaCarExercise.springboot.expections.carExceptions.AddCarException;
import RentaCarExercise.springboot.expections.carExceptions.DeleteCarException;
import RentaCarExercise.springboot.expections.carExceptions.GetByIdException;
import RentaCarExercise.springboot.expections.carExceptions.UpdateCarException;
import RentaCarExercise.springboot.model.Car;

import java.util.List;

public interface CarService {
    List<CarCreateDto> getCars();

    Car addCar(CarCreateDto car) throws AddCarException;

    CarUpdateKmDto updateCar(Long id, CarUpdateKmDto carDto) throws UpdateCarException;

    void deleteCar(Long id) throws DeleteCarException;

    Car getById(Long id) throws GetByIdException;

    CarUpdatePriceDto updatePrice(Long id, CarUpdatePriceDto car) throws UpdateCarException;
}
