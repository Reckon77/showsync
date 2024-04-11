package com.ticket.booking.showsync.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponseDTO {
    String error;
    int code;
    HttpStatus httpStatus;
}
