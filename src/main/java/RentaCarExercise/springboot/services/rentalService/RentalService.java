package RentaCarExercise.springboot.services.rentalService;

import RentaCarExercise.springboot.dto.rentalDTO.RentalCreateDto;
import RentaCarExercise.springboot.dto.rentalDTO.RentalGetDto;
import RentaCarExercise.springboot.dto.rentalDTO.RentalPostDto;
import RentaCarExercise.springboot.expections.carExceptions.GetByIdException;
import RentaCarExercise.springboot.expections.clientExpections.GetClientByIdException;
import RentaCarExercise.springboot.expections.rentalExpections.DeleteRentalException;
import RentaCarExercise.springboot.expections.rentalExpections.UpdateRentalException;

import java.util.List;

public interface RentalService {
    List<RentalGetDto> getRentals();

    void addNewRental(RentalPostDto rental) throws GetByIdException, GetClientByIdException;

    void deleteRental(long id) throws DeleteRentalException;

    void updateRental(long id, RentalCreateDto rentalCreateDto) throws UpdateRentalException;
}
