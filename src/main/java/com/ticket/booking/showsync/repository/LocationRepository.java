package com.ticket.booking.showsync.repository;

import com.ticket.booking.showsync.entity.Location;
import com.ticket.booking.showsync.entity.Theatre;
import com.ticket.booking.showsync.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location,String> {
    Optional<Location> findByLocationName(String locationName);
    Optional<Location> findByLocationId(String id);
}
