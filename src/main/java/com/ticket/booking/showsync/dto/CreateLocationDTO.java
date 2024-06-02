package com.ticket.booking.showsync.dto;

import com.ticket.booking.showsync.entity.Theatre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateLocationDTO {
    private String name;
    private List<Theatre> theatres;
}