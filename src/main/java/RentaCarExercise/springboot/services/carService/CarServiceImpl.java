package RentaCarExercise.springboot.services.carService;

import RentaCarExercise.springboot.converter.CarConverter;
import RentaCarExercise.springboot.dto.carDTO.CarCreateDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdateKmDto;
import RentaCarExercise.springboot.dto.carDTO.CarUpdatePriceDto;
import RentaCarExercise.springboot.expections.carExceptions.AddCarException;
import RentaCarExercise.springboot.expections.carExceptions.DeleteCarException;
import RentaCarExercise.springboot.expections.carExceptions.GetByIdException;
import RentaCarExercise.springboot.expections.carExceptions.UpdateCarException;
import RentaCarExercise.springboot.mapper.CarMapper;
import RentaCarExercise.springboot.model.Car;
import RentaCarExercise.springboot.repositories.CarRepository;
import RentaCarExercise.springboot.util.Messages;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static RentaCarExercise.springboot.converter.CarConverter.fromDtoToModelCar;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarCreateDto> getCars() {
        List<Car> cars = new ArrayList<>();
        cars.addAll(carRepository.findAll());
        return CarMapper.INSTANCE.EntityCarToCarDto(cars);
    }

    @Override
    public void addCar(CarCreateDto car) throws AddCarException {
        Optional<Car> carOptional = carRepository.findByPlate(car.plate());
        if (carOptional.isPresent()) {
            throw new AddCarException(Messages.PLATE_DOES_NOT_EXIST);
        }
        Car newCar = fromDtoToModelCar(car);
        carRepository.save(newCar);
    }

    @Override
    public CarUpdateKmDto updateCar(Long id, CarUpdateKmDto carDto) throws UpdateCarException {
        Car existingCar = carRepository.getReferenceById(id);
        if (existingCar == null) {
            throw new UpdateCarException(Messages.CAR_ID_DOES_NOT_EXIST);
        }
        existingCar.setKm(carDto.km());

        Car updateCar = carRepository.save(existingCar);
        return CarConverter.fromModelCarUpdateToDto(updateCar);
    }

    @Override
    public void deleteCar(Long id) throws DeleteCarException {
        Car car = carRepository.getReferenceById(id);
        if (car == null) {
            throw new DeleteCarException(Messages.CAR_ID_DOES_NOT_EXIST);
        }
        carRepository.delete(car);
    }

    public Car getById(Long id) throws GetByIdException {
        Optional<Car> carOptional = this.carRepository.findById(id);
        if (carOptional.isEmpty()) {
            throw new GetByIdException(Messages.CAR_ID_DOES_NOT_EXIST);
        }

        return carOptional.get();
    }

    public void updatePrice(Long id, CarUpdatePriceDto car) throws UpdateCarException {
        Car carPriceChange = carRepository.findById(id).orElseThrow(() -> new UpdateCarException(Messages.CAR_ID_DOES_NOT_EXIST));
        carPriceChange.setPricePerDay(car.pricePerDay());
        carRepository.save(carPriceChange);
    }

}
