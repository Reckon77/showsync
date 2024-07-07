package com.ticket.booking.showsync.service;

import com.ticket.booking.showsync.dto.CreateLocationDTO;
import com.ticket.booking.showsync.entity.*;
import com.ticket.booking.showsync.exceptions.LocationNotFoundException;
import com.ticket.booking.showsync.repository.LocationRepository;
import com.ticket.booking.showsync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Location> createLocation(CreateLocationDTO createLocationDTO, String userName){
        Optional<User> user = userRepository.findByUserName(userName);
        if(user.isEmpty()) throw new UsernameNotFoundException("User not found!");
        Location location = Location.builder()
                .pinCode(createLocationDTO.getPinCode())
                .locationName(createLocationDTO.getName()).build();
        locationRepository.save(location);

        return ResponseEntity.ok().body(location);
    }
    public ResponseEntity<List<Location>> getAllLocation(){
        return ResponseEntity.ok().body(locationRepository.findAll());
    }
    public Location getLoactionById(String locationId){
        Optional<Location> location = locationRepository.findByLocationId(locationId);
        if(location.isEmpty()){
            throw new LocationNotFoundException(locationId+ " - locationID not found in the location database");
        }
        return location.get();
    }
}
