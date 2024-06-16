package com.ticket.booking.showsync.repository;

import com.ticket.booking.showsync.entity.Show;
import com.ticket.booking.showsync.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show,String> {
    List<Show> findByScreen_ScreenId(String screenId);
}
