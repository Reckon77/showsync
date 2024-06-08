package com.ticket.booking.showsync.controller;

import com.ticket.booking.showsync.dto.CreateLocationDTO;
import com.ticket.booking.showsync.entity.Location;
import com.ticket.booking.showsync.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/location")
    public ResponseEntity<Location> createTheatre(@RequestBody CreateLocationDTO createLocationDTO, Authentication authentication) {
        String user = authentication.getName();
        return locationService.createLocation(createLocationDTO, user);
    }
    @GetMapping("/location/all")
    public ResponseEntity<List<Location>> getAllLocation(){
        return locationService.getAllLocation();
    }
}
