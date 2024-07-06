package com.ticket.booking.showsync.service;

import com.ticket.booking.showsync.dto.CreateMovieDTO;
import com.ticket.booking.showsync.entity.Location;
import com.ticket.booking.showsync.entity.Movie;
import com.ticket.booking.showsync.exceptions.LocationNotFoundException;
import com.ticket.booking.showsync.repository.LocationRepository;
import com.ticket.booking.showsync.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private LocationRepository locationRepository;
    public ResponseEntity<Movie> createMovie(CreateMovieDTO createMovieDTO){
        //TODO : add movie mapper
        val movie = Movie.builder()
                .movieLength(createMovieDTO.getMovieLength())
                .movieDesc(createMovieDTO.getMovieDesc())
                .movieName(createMovieDTO.getMovieName())
                .rated(createMovieDTO.getRated())
                .genre(createMovieDTO.getGenre())
                .language(createMovieDTO.getLanguage())
                .releaseDate(createMovieDTO.getReleaseDate())
                .build();
        return ResponseEntity.ok().body(movieRepository.save(movie));
    }
    public ResponseEntity<List<Movie>> getMovieByLocation(String locationId){
        Optional<Location> location = locationRepository.findByLocationId(locationId);
        if(location.isEmpty()){
            throw new LocationNotFoundException(locationId+ " - locationID not found in the location database");
        }
        return ResponseEntity.ok().body(movieRepository.findMoviesByLocationId(locationId));
    }

}
