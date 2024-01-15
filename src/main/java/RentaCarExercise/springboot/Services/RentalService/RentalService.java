package RentaCarExercise.springboot.Services.RentalService;

import RentaCarExercise.springboot.Dto.RentalDTO.RentalCreateDto;
import RentaCarExercise.springboot.Dto.RentalDTO.RentalPostDto;
import RentaCarExercise.springboot.Expections.CarExceptions.GetByIdException;
import RentaCarExercise.springboot.Expections.ClientExpections.GetClientByIdException;
import RentaCarExercise.springboot.Expections.RentalExpections.DeleteRentalException;
import RentaCarExercise.springboot.Expections.RentalExpections.UpdateRentalException;

import java.util.List;

public interface RentalService {
    List<RentalCreateDto> getRentals();

    void addNewRental(RentalPostDto rental) throws GetByIdException, GetClientByIdException;

    void deleteRental(RentalCreateDto rentalId) throws DeleteRentalException;

    void updateRental(long id, RentalCreateDto rentalCreateDto) throws UpdateRentalException;
}
