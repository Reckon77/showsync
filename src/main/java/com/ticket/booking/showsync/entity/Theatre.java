package com.ticket.booking.showsync.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "theatre")
    Set<Screen> screens = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "theatre")
    Set<Show> shows = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "theatre")
    Set<Movie> movies = new HashSet<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "location_id")
    Location location;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

}
