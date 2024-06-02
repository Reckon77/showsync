package com.ticket.booking.showsync.repository;

import com.ticket.booking.showsync.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,String> {
    List<Theatre> findByLocationId(String locationId);
}
