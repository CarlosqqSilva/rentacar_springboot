package RentaCarExercise.springboot.controllers;

import RentaCarExercise.springboot.dto.carDTO.CarCreateDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdateDto;
import RentaCarExercise.springboot.expections.carExceptions.AddCarException;
import RentaCarExercise.springboot.expections.carExceptions.DeleteCarException;
import RentaCarExercise.springboot.expections.carExceptions.UpdateCarException;
import RentaCarExercise.springboot.model.Client;
import RentaCarExercise.springboot.services.carService.CarService;
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

    private final CarService carsService;

    @Autowired
    public CarController(CarService carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CarCreateDto>> getCars() {
        return ResponseEntity.ok(carsService.getCars());
    }

    @PostMapping("/")
    public ResponseEntity<Client> addCar(@Valid @RequestBody CarCreateDto car) throws AddCarException {
        carsService.addCar(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarUpdateDto> updateCar(@PathVariable Long id, @Valid @RequestBody CarUpdateDto carDto, BindingResult bindingResult) throws UpdateCarException {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CarUpdateDto savedCar = carsService.updateCar(id, carDto);
        return new ResponseEntity<>(savedCar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) throws DeleteCarException {
        carsService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
