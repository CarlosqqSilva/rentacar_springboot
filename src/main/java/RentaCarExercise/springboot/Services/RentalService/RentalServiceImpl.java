package RentaCarExercise.springboot.Services.RentalService;

import RentaCarExercise.springboot.Dto.RentalDTO.RentalCreateDto;
import RentaCarExercise.springboot.Dto.RentalDTO.RentalPostDto;
import RentaCarExercise.springboot.Entity.Car;
import RentaCarExercise.springboot.Entity.Client;
import RentaCarExercise.springboot.Entity.Rental;
import RentaCarExercise.springboot.Expections.CarExceptions.GetByIdException;
import RentaCarExercise.springboot.Expections.ClientExpections.GetClientByIdException;
import RentaCarExercise.springboot.Expections.RentalExpections.DeleteRentalException;
import RentaCarExercise.springboot.Expections.RentalExpections.UpdateRentalException;
import RentaCarExercise.springboot.Mapper.RentalMapper;
import RentaCarExercise.springboot.Repositories.RentalRepository;
import RentaCarExercise.springboot.Services.CarService.CarService;
import RentaCarExercise.springboot.Services.ClientService.ClientService;
import RentaCarExercise.springboot.Util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final ClientService clientService;
    private final CarService carService;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalsRepository, ClientService clientService, CarService carService) {

        this.rentalRepository = rentalsRepository;
        this.clientService = clientService;
        this.carService = carService;
    }

    public List<RentalCreateDto> getRentals() {
        List<Rental> rentals = new ArrayList<>();
        rentals.addAll(rentalRepository.findAll());
        return RentalMapper.INSTANCE.EntityRentalToRentalDto(rentals);
    }

    @Override
    public void addNewRental(RentalPostDto rental) throws GetByIdException, GetClientByIdException {

        Car car = carService.getById(rental.CarID());
        Client client = clientService.getById(rental.ClientID());
        Rental newRental = new Rental(client, car, rental.endOfRental());
        rentalRepository.save(newRental);
    }

    public void deleteRental(RentalCreateDto rentalId) throws DeleteRentalException {
        Rental rentalToDelete = rentalRepository.getReferenceById(rentalId.id());
        if (rentalToDelete == null) {
            throw new DeleteRentalException(Messages.RENTAL_ID_DOES_NOT_EXIST);
        }
        rentalRepository.delete(rentalToDelete);
    }

    public void updateRental(long id, RentalCreateDto rentalCreateDto) throws UpdateRentalException {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isEmpty()) {
            throw new UpdateRentalException(Messages.RENTAL_ID_DOES_NOT_EXIST);
        }

        Rental existingRental = optionalRental.get();
        rentalRepository.save(existingRental);
    }
}
