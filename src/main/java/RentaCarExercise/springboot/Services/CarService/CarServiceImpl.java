package RentaCarExercise.springboot.Services.CarService;

import RentaCarExercise.springboot.Converter.CarConverter;
import RentaCarExercise.springboot.Dto.CarDTO.CarCreateDto;
import RentaCarExercise.springboot.Dto.CarDTO.CarUpdateDto;
import RentaCarExercise.springboot.Entity.Car;
import RentaCarExercise.springboot.Expections.CarExceptions.AddCarException;
import RentaCarExercise.springboot.Expections.CarExceptions.DeleteCarException;
import RentaCarExercise.springboot.Expections.CarExceptions.GetByIdException;
import RentaCarExercise.springboot.Expections.CarExceptions.UpdateCarException;
import RentaCarExercise.springboot.Mapper.CarMapper;
import RentaCarExercise.springboot.Repositories.CarRepository;
import RentaCarExercise.springboot.Util.Messages;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static RentaCarExercise.springboot.Converter.CarConverter.fromDtoToEntityCar;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

//    @Override
//    public List<CarCreateDto> getCars() {
//        List<Car> cars = carRepository.findAll();
//        return cars.stream()
//                .map(CarConverter::fromEntityCarToDto)
//                .toList();
//    }

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
        Car newCar = fromDtoToEntityCar(car);
        carRepository.save(newCar);
    }

    @Override
    public CarUpdateDto updateCar(Long id, CarUpdateDto carDto) throws UpdateCarException {
        Car existingCar = carRepository.getReferenceById(id);
        if (existingCar == null) {
            throw new UpdateCarException(Messages.CAR_ID_DOES_NOT_EXIST);
        }
        existingCar.setKm(carDto.km());

        Car updateCar = carRepository.save(existingCar);
        return CarConverter.fromEntityCarUpdateToDto(updateCar);
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

}
