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
@Table(name = "seat_category")
public class SeatCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String seatCategoryId;
    private String name;
    private int capacity;
    private int price;
    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "screen_id")
    Screen screen;

}
