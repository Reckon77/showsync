package com.ticket.booking.showsync;


import com.ticket.booking.showsync.dto.ErrorResponseDTO;
import com.ticket.booking.showsync.exceptions.ExistingUserException;
import com.ticket.booking.showsync.exceptions.LocationNotFoundException;
import com.ticket.booking.showsync.exceptions.RegistrationFailedException;
import com.ticket.booking.showsync.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ExistingUserException.class,
            MethodArgumentNotValidException.class,
            LocationNotFoundException.class,
            IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<ErrorResponseDTO> handleException(RegistrationFailedException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponseDTO.builder()
                        .error(ex.getMessage())
                        .code(HttpStatus.SERVICE_UNAVAILABLE.value())
                        .httpStatus(HttpStatus.SERVICE_UNAVAILABLE)
                        .build()
        );
    }

    @ExceptionHandler({BadCredentialsException.class, AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseDTO> handleException(AuthenticationException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(
                ErrorResponseDTO.builder()
                        .error(ex.getMessage())
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .httpStatus(HttpStatus.UNAUTHORIZED)
                        .build()
        );
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleException(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                ErrorResponseDTO.builder()
                        .error(ex.getMessage())
                        .code(HttpStatus.NOT_FOUND.value())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build()
        );
    }
}
