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
@Table(name = "screens")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String screenId;
    private String name;
    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "theatre_id")
    Theatre theatre;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "screen")
    Set<SeatCategory> seatCategories = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "screen")
    Set<Seat> seats = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "screen")
    Set<Show> shows = new HashSet<>();
}
