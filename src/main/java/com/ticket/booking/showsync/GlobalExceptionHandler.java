package com.ticket.booking.showsync;


import com.ticket.booking.showsync.dto.ErrorResponseDTO;
import com.ticket.booking.showsync.exceptions.ExistingUserException;
import com.ticket.booking.showsync.exceptions.RegistrationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ExistingUserException.class,
            MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex){
        return ResponseEntity.badRequest().body(
                ErrorResponseDTO.builder()
                        .error(ex.getMessage())
                        .code(HttpStatus.BAD_REQUEST.value())
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .build()
        );
    }
    @ExceptionHandler(RegistrationFailedException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(RegistrationFailedException ex){
        return ResponseEntity.badRequest().body(
                ErrorResponseDTO.builder()
                        .error(ex.getMessage())
                        .code(HttpStatus.SERVICE_UNAVAILABLE.value())
                        .httpStatus(HttpStatus.SERVICE_UNAVAILABLE)
                        .build()
        );
    }
}
