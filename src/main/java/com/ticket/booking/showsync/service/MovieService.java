package com.ticket.booking.showsync.service;

import com.ticket.booking.showsync.dto.CreateMovieDTO;
import com.ticket.booking.showsync.entity.Movie;
import com.ticket.booking.showsync.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public ResponseEntity<Movie> createMovie(CreateMovieDTO createMovieDTO){
        //TODO : add movie mapper
        val movie = Movie.builder()
                .movieLength(createMovieDTO.getMovieLength())
                .movieDesc(createMovieDTO.getMovieDesc())
                .movieName(createMovieDTO.getMovieName())
                .rated(createMovieDTO.getRated())
                .genre(createMovieDTO.getGenre())
                .language(createMovieDTO.getLanguage())
                .build();
        return ResponseEntity.ok().body(movieRepository.save(movie));
    }

}
