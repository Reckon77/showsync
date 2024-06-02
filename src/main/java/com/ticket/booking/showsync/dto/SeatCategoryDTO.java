package com.ticket.booking.showsync.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatCategoryDTO {
    private String name;
    private int capacity;
    private int price;
}
