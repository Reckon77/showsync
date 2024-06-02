package com.ticket.booking.showsync.repository;

import com.ticket.booking.showsync.config.UserInfoUserDetails;
import com.ticket.booking.showsync.entity.Theatre;
import com.ticket.booking.showsync.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,String> {

    Optional<Theatre> findByName(String theaterName);
    List<Theatre> findAllByUser(User user);

//    Optional<List<Theatre>> findByLocation(String location);


}
