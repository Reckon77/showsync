package com.ticket.booking.showsync.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTheatreDTO {
    private String name;
    private AddressDTO address;
    private List<ScreenDTO> screens;
}
