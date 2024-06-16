package com.ticket.booking.showsync.service;

import com.ticket.booking.showsync.dto.CreateShowDTO;
import com.ticket.booking.showsync.entity.Movie;
import com.ticket.booking.showsync.entity.Screen;
import com.ticket.booking.showsync.entity.Show;
import com.ticket.booking.showsync.exceptions.ResourceNotFoundException;
import com.ticket.booking.showsync.repository.MovieRepository;
import com.ticket.booking.showsync.repository.ScreenRepository;
import com.ticket.booking.showsync.repository.ShowRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShowService {
    @Autowired
    ShowRepository repository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ScreenRepository screenRepository;
    public ResponseEntity<Show> createShow(CreateShowDTO showDTO){
        //validate the movie
        Optional<Movie> movie=movieRepository.findById(showDTO.getMovieId());
        if(movie.isEmpty()){
            throw new ResourceNotFoundException("Movie with id "+showDTO.getMovieId()+" not found");
        }
        val movieObj=movie.get();
        //validate the screen
        Optional<Screen> screen = screenRepository.findById(showDTO.getScreenId());
        if(screen.isEmpty()){
            throw new ResourceNotFoundException("Screen with id "+showDTO.getScreenId()+" not found");
        }
        val screenObj=screen.get();
        //check release date
        if(showDTO.getStartTime().compareTo(movieObj.getReleaseDate())<0){
                throw new IllegalArgumentException("Show timing cannot be before release date : show time -> "+showDTO.getStartTime()+" movie release date -> "+"movieObj.getReleaseDate()");
        }
        val startTime = showDTO.getStartTime();
        //get the screen details and check if any other shows in the show start time
        val preExistingShows = repository.findByScreen_ScreenId(showDTO.getScreenId());
        Set<Show> existingShows=preExistingShows.stream()
                .filter((show)-> show.getStartTime().compareTo(startTime)<0&& show.getEndTime().compareTo(startTime)>0).collect(Collectors.toSet());
        if(!existingShows.isEmpty()){
            //throw exception
            log.error("Show already exist start time");
            throw new IllegalArgumentException("Another show exist in the same time");
        }
        val endTime = getEndDate(showDTO.getStartTime(),movieObj.getMovieLength());
        val show=Show.builder()
                .startTime(showDTO.getStartTime())
                .endTime(endTime)
                .screen(screenObj)
                .movie(movieObj)
                .language(showDTO.getLanguage())
                .build();
        return ResponseEntity.ok().body(repository.save(show));
    }
    public Date getEndDate(Date startTime, int minutes){
        LocalDateTime localDateTime = startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime updatedLocalDateTime = localDateTime.plusMinutes(minutes);
        return Date.from(updatedLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
