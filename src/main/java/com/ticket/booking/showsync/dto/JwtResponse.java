package com.ticket.booking.showsync.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    String jwtToken;
    String userName;
}
