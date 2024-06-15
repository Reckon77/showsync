package com.ticket.booking.showsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

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
    private Date startTime;
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
