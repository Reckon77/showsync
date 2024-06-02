package com.ticket.booking.showsync.repository;

import com.ticket.booking.showsync.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen,String> {
}
