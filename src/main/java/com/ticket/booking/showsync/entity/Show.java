package com.ticket.booking.showsync.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String showId;
    private String language;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private Date startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private Date endTime;
    @ManyToOne
    @JoinColumn(name = "theatre_id")
    Theatre theatre;
    @ManyToOne
    @JoinColumn(name="movie_id")
    Movie movie;
    @ManyToOne
    @JoinColumn(name = "screen_id")
    Screen screen;

}
