package RentaCarExercise.springboot.aspects;

import RentaCarExercise.springboot.expections.carExceptions.AddCarException;
import RentaCarExercise.springboot.expections.carExceptions.DeleteCarException;
import RentaCarExercise.springboot.expections.carExceptions.GetByIdException;
import RentaCarExercise.springboot.expections.carExceptions.UpdateCarException;
import RentaCarExercise.springboot.expections.clientExpections.CreateClientException;
import RentaCarExercise.springboot.expections.clientExpections.DeleteClientException;
import RentaCarExercise.springboot.expections.clientExpections.GetClientByIdException;
import RentaCarExercise.springboot.expections.clientExpections.UpdateClientException;
import RentaCarExercise.springboot.expections.rentalExpections.DeleteRentalException;
import RentaCarExercise.springboot.expections.rentalExpections.UpdateRentalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ControllerAdvice
public class ServiceExceptionsHandler {

    private static final Logger logger = LoggerFactory.getLogger(ServiceExceptionsHandler.class);

    @ExceptionHandler(value = {AddCarException.class, DeleteCarException.class, GetByIdException.class, UpdateCarException.class})
    public ResponseEntity<String> handlerCarService(Exception ex) {
        logger.error("Known Car Exception " + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(value = {CreateClientException.class, DeleteClientException.class, GetClientByIdException.class, UpdateClientException.class})
    public ResponseEntity<String> handlerClientService(Exception ex) {
        logger.error("Known Client Exception " + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(value = {DeleteRentalException.class, UpdateRentalException.class})
    public ResponseEntity<String> handlerRentalService(Exception ex) {
        logger.error("Known Rental Exception " + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handlerGenericException(Exception ex) {
        logger.error("Unknown Exception  " + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error happened");
    }
}
