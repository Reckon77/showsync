package com.ticket.booking.showsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String movieId;
    private String movieName;
    private String movieDesc;
    private String genre;
    private int movieLength;
    private String language; //TODO : Change to ENUM
    private String rated; //TODO : Change to ENUM
    private Date releaseDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "movie")
    Set<MovieTicket> movieTicket = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "movie")
    Set<Show> show = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "theatre_id")
    Theatre theatre;
}
