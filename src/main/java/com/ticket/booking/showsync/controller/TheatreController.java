package com.ticket.booking.showsync.controller;

import com.ticket.booking.showsync.dto.CreateTheatreDTO;
import com.ticket.booking.showsync.entity.Theatre;
import com.ticket.booking.showsync.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheatreController {
    @Autowired
    TheatreService theatreService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/theatre")
    public ResponseEntity<Theatre> createTheatre(@RequestBody CreateTheatreDTO createTheatreDTO, Authentication authentication){
        String user = authentication.getName();
        return theatreService.createTheatre(createTheatreDTO,user);
    }
}
