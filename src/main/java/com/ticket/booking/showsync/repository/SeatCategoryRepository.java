package com.ticket.booking.showsync.repository;

import com.ticket.booking.showsync.entity.Screen;
import com.ticket.booking.showsync.entity.SeatCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatCategoryRepository extends JpaRepository<SeatCategory,String> {
}
