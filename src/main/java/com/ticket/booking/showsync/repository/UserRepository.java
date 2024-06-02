package com.ticket.booking.showsync.repository;

import com.ticket.booking.showsync.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUserName(String userName);
//    Optional<User> findById(String id);
}
