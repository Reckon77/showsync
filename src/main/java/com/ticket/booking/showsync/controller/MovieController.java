package com.ticket.booking.showsync.controller;

import com.ticket.booking.showsync.dto.CreateMovieDTO;
import com.ticket.booking.showsync.entity.Movie;
import com.ticket.booking.showsync.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/movie")
    public ResponseEntity<Movie> createMovie(@RequestBody CreateMovieDTO createMovieDTO){
        return movieService.createMovie(createMovieDTO);
    }
    @GetMapping("/movie/{locationId}")
    public ResponseEntity<List<Movie>> getMoviesByLocation(@PathVariable String locationId){
        return movieService.getMovieByLocation(locationId);
    }
}
