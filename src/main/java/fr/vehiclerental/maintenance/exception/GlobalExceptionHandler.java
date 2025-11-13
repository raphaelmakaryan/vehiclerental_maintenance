package fr.vehiclerental.maintenance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorEntity> badRequestHandler(BadRequestException exception) {
        ErrorEntity error = new ErrorEntity(false, LocalDateTime.now(), exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorEntity> runtimeExceptionHandler(RuntimeException exception) {
        ErrorEntity error = new ErrorEntity(false, LocalDateTime.now(), exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(error);
    }

    @ExceptionHandler(MaintenanceNotFind.class)
    public ResponseEntity<ErrorEntity> MaintenanceNotFind(MaintenanceNotFind exception) {
        ErrorEntity error = new ErrorEntity(false, LocalDateTime.now(), exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(error);
    }

    @ExceptionHandler(UnavailabilityNotFind.class)
    public ResponseEntity<ErrorEntity> unavailabilityNotFind(UnavailabilityNotFind exception) {
        ErrorEntity error = new ErrorEntity(false, LocalDateTime.now(), exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(error);
    }

    @ExceptionHandler(VehicleAlreadyReserved.class)
    public ResponseEntity<ErrorEntity> vehicleAlreadyReserved(VehicleAlreadyReserved exception) {
        ErrorEntity error = new ErrorEntity(false, LocalDateTime.now(), exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(error);
    }

    @ExceptionHandler(VehicleNotFind.class)
    public ResponseEntity<ErrorEntity> vehicleNotFind(VehicleNotFind exception) {
        ErrorEntity error = new ErrorEntity(false, LocalDateTime.now(), exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(error);
    }

    @ExceptionHandler(VehicleType.class)
    public ResponseEntity<ErrorEntity> vehicleType(VehicleType exception) {
        ErrorEntity error = new ErrorEntity(false, LocalDateTime.now(), exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(error);
    }

}
