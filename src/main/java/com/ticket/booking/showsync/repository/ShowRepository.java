package com.ticket.booking.showsync.repository;

import com.ticket.booking.showsync.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show,String> {
}
