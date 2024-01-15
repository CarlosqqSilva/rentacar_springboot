package RentaCarExercise.springboot.controllers;

import RentaCarExercise.springboot.dto.rentalDTO.RentalCreateDto;
import RentaCarExercise.springboot.dto.rentalDTO.RentalPostDto;
import RentaCarExercise.springboot.expections.carExceptions.GetByIdException;
import RentaCarExercise.springboot.expections.clientExpections.GetClientByIdException;
import RentaCarExercise.springboot.expections.rentalExpections.DeleteRentalException;
import RentaCarExercise.springboot.expections.rentalExpections.UpdateRentalException;
import RentaCarExercise.springboot.model.Rental;
import RentaCarExercise.springboot.services.rentalService.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rentals")
public class RentalController {

    private final RentalService rentalsService;

    @Autowired
    public RentalController(RentalService rentalsService) {
        this.rentalsService = rentalsService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RentalCreateDto>> getRentals() {
        return ResponseEntity.ok(rentalsService.getRentals());
    }

    @PostMapping("/newRental")
    public ResponseEntity<Rental> addNewRental(@Valid @RequestBody RentalPostDto rental, BindingResult bindingResult) throws GetClientByIdException, GetByIdException {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        rentalsService.addNewRental(rental);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<RentalCreateDto> updateRental(@PathVariable long id, @Valid @RequestBody RentalCreateDto rentalDto, BindingResult bindingResult) throws UpdateRentalException {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        rentalsService.updateRental(id, rentalDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RentalCreateDto> deleteRental(@PathVariable RentalCreateDto rentalId) throws DeleteRentalException {
        rentalsService.deleteRental(rentalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
