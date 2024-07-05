package com.ticket.booking.showsync.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "screens")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String screenId;
    private String name;
    private String slots;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "theatre_id",referencedColumnName = "theatreId")
    Theatre theatre;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "screen")
    List<SeatCategory> seatCategories = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "screen")
    Set<Seat> seats = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "screen")
    Set<Show> shows = new HashSet<>();
}
