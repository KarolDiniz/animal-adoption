package br.edu.ifpb.dac.karoline.animaladoption.business.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>("An internal server error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserHasAnimalsException.class)
    public ResponseEntity<String> handleUserHasAnimalsException(UserHasAnimalsException ex) {
        return new ResponseEntity<>("Unable to delete the user. The user is associated with one or more animals.", HttpStatus.BAD_REQUEST);
    }
}
