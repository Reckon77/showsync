package com.ticket.booking.showsync.service;

import com.ticket.booking.showsync.dto.CreateTheatreDTO;
import com.ticket.booking.showsync.dto.ScreenDTO;
import com.ticket.booking.showsync.dto.SeatCategoryDTO;
import com.ticket.booking.showsync.entity.Screen;
import com.ticket.booking.showsync.entity.SeatCategory;
import com.ticket.booking.showsync.entity.Theatre;
import com.ticket.booking.showsync.entity.User;
import com.ticket.booking.showsync.repository.TheatreRepository;
import com.ticket.booking.showsync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TheatreService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public ResponseEntity<Theatre> createTheatre(CreateTheatreDTO createTheatreDTO,String userName){
        Optional<User> user = userRepository.findByUserName(userName);
        User userObj=null;
        if(user.isPresent()){
            userObj=user.get();
        }else {
            throw new UsernameNotFoundException("User not found");
        }
        Theatre theatre = Theatre.builder()
                .name(createTheatreDTO.getName())
                .build();
        Set<Screen> screens = createTheatreDTO.getScreens().stream()
                .map(screenDTO -> {
                    Screen screen = new Screen();
                    screen.setName(screenDTO.getName());
//                    screen.setTheatre(theatre);
                    Set<SeatCategory> seatCategories = screenDTO.getSeatCategories().stream()
                                    .map(seatCategoryDTO -> {
                                        SeatCategory seatCategory = new SeatCategory();
                                        seatCategory.setName(seatCategoryDTO.getName());
                                        seatCategory.setCapacity(seatCategoryDTO.getCapacity());
                                        seatCategory.setPrice(seatCategoryDTO.getPrice());
//                                        seatCategory.setScreen(screen);
                                        return seatCategory;
                                    }).collect(Collectors.toSet());
                    screen.setSeatCategories(seatCategories);
                    return  screen;
                }).collect(Collectors.toSet());

        theatre.setScreens(screens);
        theatre.setUser(userObj);
        theatreRepository.save(theatre);
        return ResponseEntity.ok().body(theatre);
    }
}
