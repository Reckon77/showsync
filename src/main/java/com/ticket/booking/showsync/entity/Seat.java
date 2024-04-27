package com.ticket.booking.showsync.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String seatId;
    private Boolean bookingStatus=false;
    @ManyToOne
    @JoinColumn(name = "screen_id")
    Screen screen;
    @Column(name = "seat_category_id")
    String seatCategoryId;
    @OneToOne
    @JoinColumn(name = "ticket_id")
    MovieTicket ticket;
}
