package RentaCarExercise.springboot.Aspects;

import RentaCarExercise.springboot.Expections.CarExceptions.AddCarException;
import RentaCarExercise.springboot.Expections.CarExceptions.DeleteCarException;
import RentaCarExercise.springboot.Expections.CarExceptions.GetByIdException;
import RentaCarExercise.springboot.Expections.CarExceptions.UpdateCarException;
import RentaCarExercise.springboot.Expections.ClientExpections.CreateClientException;
import RentaCarExercise.springboot.Expections.ClientExpections.DeleteClientException;
import RentaCarExercise.springboot.Expections.ClientExpections.GetClientByIdException;
import RentaCarExercise.springboot.Expections.ClientExpections.UpdateClientException;
import RentaCarExercise.springboot.Expections.RentalExpections.DeleteRentalException;
import RentaCarExercise.springboot.Expections.RentalExpections.UpdateRentalException;
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
