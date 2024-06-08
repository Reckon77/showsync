package com.ticket.booking.showsync.controller;

import com.ticket.booking.showsync.dto.CreateTheatreDTO;
import com.ticket.booking.showsync.entity.Theatre;
import com.ticket.booking.showsync.repository.TheatreRepository;
import com.ticket.booking.showsync.service.TheatreService;
import com.ticket.booking.showsync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheatreController {
    @Autowired
    TheatreService theatreService;



    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/theatre")
    public ResponseEntity<Theatre> createTheatre(@RequestBody CreateTheatreDTO createTheatreDTO, Authentication authentication){
        String user = authentication.getName();
        return theatreService.createTheatre(createTheatreDTO,user);
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(value = "/get-theatre",method = RequestMethod.GET)
    public ResponseEntity<Theatre> getTheatre(@RequestParam("query") String query, Authentication authentication){
        String user = authentication.getName();
        return theatreService.getTheatre(query,user)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());

    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(value = "/get-all-theatre",method = RequestMethod.GET)
    public ResponseEntity<List<Theatre>> getAllTheatre(Authentication authentication){
        String userName = authentication.getName();
        return theatreService.getAllTheatre(userName)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/theatre/{id}")
    public ResponseEntity<List<Theatre>> getAllTheatreByLocation(@PathVariable("id") String id){
        return theatreService.getAllTheatreByLocation(id);
    }

}
