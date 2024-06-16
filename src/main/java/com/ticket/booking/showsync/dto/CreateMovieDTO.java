package com.ticket.booking.showsync.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMovieDTO {
    //TODO : add validation (null checks)
    private String movieName;
    private String movieDesc;
    private String genre;
    private int movieLength;
    private String language;
    private String rated;
    private Date releaseDate;
}
