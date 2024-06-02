package com.ticket.booking.showsync.service;

import com.ticket.booking.showsync.dto.CreateLocationDTO;
import com.ticket.booking.showsync.entity.*;
import com.ticket.booking.showsync.repository.LocationRepository;
import com.ticket.booking.showsync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

//    public ResponseEntity<Location> getLocation(String locationName) {
//
//    }

    public ResponseEntity<Location> createLocation(CreateLocationDTO createLocationDTO, String userName){
        Optional<User> user = userRepository.findByUserName(userName);
        User userObj;
        if(user.isPresent()){
            userObj = user.get();
        }else {
            throw new UsernameNotFoundException("User not found");
        }
        Location location = Location.builder()
                .locationName(createLocationDTO.getName()).build();

        Set<Theatre> theatres = createLocationDTO.getTheatres().stream()
                .map(theatreDTO -> {
                    Theatre theatre = new Theatre();
                    theatre.setName(theatreDTO.getName());
                    theatre.setLocation(theatreDTO.getLocation());
                    return theatre;
                }).collect(Collectors.toSet());
        location.setTheatres(theatres);
        locationRepository.save(location);

        return ResponseEntity.ok().body(location);
    }
}
