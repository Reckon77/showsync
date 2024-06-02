package com.ticket.booking.showsync.repository;

import com.ticket.booking.showsync.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,String> { }
