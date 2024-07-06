package com.ticket.booking.showsync.repository;

import com.ticket.booking.showsync.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {
    @Query("SELECT m FROM Movie m " +
            "INNER JOIN Show s ON m.movieId = s.movie.movieId " +
            "INNER JOIN Screen sc ON s.screen.screenId = sc.screenId " +
            "INNER JOIN Theatre t ON sc.theatre.theatreId = t.theatreId " +
            "INNER JOIN Location l ON t.location.locationId = l.locationId " +
            "WHERE l.locationId = :locationId")
    List<Movie> findMoviesByLocationId(@Param("locationId") String locationId);
}
