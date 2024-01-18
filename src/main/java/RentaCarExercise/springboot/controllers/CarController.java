package RentaCarExercise.springboot.controllers;

import RentaCarExercise.springboot.dto.carDTO.CarCreateDto;
import RentaCarExercise.springboot.dto.carDTO.CarGetDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdateKmDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdatePriceDto;
import RentaCarExercise.springboot.expections.carExceptions.AddCarException;
import RentaCarExercise.springboot.expections.carExceptions.DeleteCarException;
import RentaCarExercise.springboot.expections.carExceptions.UpdateCarException;
import RentaCarExercise.springboot.model.Car;
import RentaCarExercise.springboot.services.carService.CarServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {

    private final CarServiceImpl carsService;

    @Autowired
    public CarController(CarServiceImpl carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CarGetDto>> getCars() {
        return ResponseEntity.ok(carsService.getCars());
    }

    @PostMapping("/")
    public ResponseEntity<Car> addCar(@Valid @RequestBody CarCreateDto car) throws AddCarException {
        Car car1 = carsService.addCar(car);
        return new ResponseEntity<>(car1, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarUpdateKmDto> updateCar(@PathVariable Long id, @Valid @RequestBody CarUpdateKmDto carDto, BindingResult bindingResult) throws UpdateCarException {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CarUpdateKmDto savedCar = carsService.updateCar(id, carDto);
        return new ResponseEntity<>(savedCar, HttpStatus.OK);
    }

    @PatchMapping("/price/{id}")
    public ResponseEntity<String> updatePrice(@Valid @RequestBody CarUpdatePriceDto car, @PathVariable Long id) throws UpdateCarException {
        carsService.updatePrice(id, car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) throws DeleteCarException, DeleteCarException {
        carsService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
