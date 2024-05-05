package com.ticket.booking.showsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "theatres")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String theatreId;
    private String name;
    //private String location;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "theatre")
    Set<Screen> screens = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "theatre")
    Set<Show> shows = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "theatre")
    Set<Movie> movies = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "location_id")
    Location location;

}
