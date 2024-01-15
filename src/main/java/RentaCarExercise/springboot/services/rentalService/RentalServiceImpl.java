package RentaCarExercise.springboot.services.rentalService;

import RentaCarExercise.springboot.dto.rentalDTO.RentalCreateDto;
import RentaCarExercise.springboot.dto.rentalDTO.RentalPostDto;
import RentaCarExercise.springboot.expections.carExceptions.GetByIdException;
import RentaCarExercise.springboot.expections.clientExpections.GetClientByIdException;
import RentaCarExercise.springboot.expections.rentalExpections.DeleteRentalException;
import RentaCarExercise.springboot.expections.rentalExpections.UpdateRentalException;
import RentaCarExercise.springboot.mapper.RentalMapper;
import RentaCarExercise.springboot.model.Car;
import RentaCarExercise.springboot.model.Client;
import RentaCarExercise.springboot.model.Rental;
import RentaCarExercise.springboot.repositories.RentalRepository;
import RentaCarExercise.springboot.services.carService.CarService;
import RentaCarExercise.springboot.services.clientService.ClientService;
import RentaCarExercise.springboot.util.Messages;
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
    private RentalMapper rentalMapper;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalsRepository, ClientService clientService, CarService carService) {

        this.rentalRepository = rentalsRepository;
        this.clientService = clientService;
        this.carService = carService;
    }

    public List<RentalCreateDto> getRentals() {
        List<Rental> rentals = new ArrayList<>();
        rentals.addAll(rentalRepository.findAll());
        return rentalMapper.modelRentalToRentalDto(rentals);
    }

    @Override
    public void addNewRental(RentalPostDto rental) throws GetByIdException, GetClientByIdException {

        Car car = carService.getById(rental.CarID());
        Client client = clientService.getById(rental.ClientID());
        Rental newRental = new Rental(client, car, rental.endOfRental());
        rentalRepository.save(newRental);
    }

    public void deleteRental(long id) throws DeleteRentalException {
        boolean exists = rentalRepository.existsById(id);
        if (!exists) {
            throw new DeleteRentalException(Messages.RENTAL_ID_DOES_NOT_EXIST);
        }
        rentalRepository.deleteById(id);
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
