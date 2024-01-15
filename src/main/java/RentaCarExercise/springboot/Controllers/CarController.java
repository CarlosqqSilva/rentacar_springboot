package RentaCarExercise.springboot.Controllers;

import RentaCarExercise.springboot.Dto.CarDTO.CarCreateDto;
import RentaCarExercise.springboot.Dto.CarDTO.CarUpdateDto;
import RentaCarExercise.springboot.Entity.Client;
import RentaCarExercise.springboot.Expections.CarExceptions.AddCarException;
import RentaCarExercise.springboot.Expections.CarExceptions.DeleteCarException;
import RentaCarExercise.springboot.Expections.CarExceptions.UpdateCarException;
import RentaCarExercise.springboot.Services.CarService.CarServiceImpl;
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
