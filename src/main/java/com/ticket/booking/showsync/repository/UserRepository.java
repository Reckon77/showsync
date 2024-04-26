package com.ticket.booking.showsync.repository;

import com.ticket.booking.showsync.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);
//    Optional<User> findById(String id);
}
