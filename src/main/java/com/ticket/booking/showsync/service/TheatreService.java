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
                                        seatCategory.setScreen(screen);
                                        return seatCategory;
                                    }).collect(Collectors.toSet());
                    screen.setSeatCategories(seatCategories);
                    return  screen;
                }).collect(Collectors.toSet());
////         Create a set to store screens
//        Set<Screen> screens = new HashSet<>();
//
//// Iterate over each ScreenDTO
//        for (ScreenDTO screenDTO : createTheatreDTO.getScreens()) {
//            // Create a new Screen object
//            Screen screen = new Screen();
//            screen.setName(screenDTO.getName());
////            screen.setTheatre(theatre);
//
//            // Create a set to store seat categories for this screen
////            Set<SeatCategory> seatCategories = new HashSet<>();
////
////            // Iterate over each SeatCategoryDTO for this ScreenDTO
////            for (SeatCategoryDTO seatCategoryDTO : screenDTO.getSeatCategories()) {
////                // Create a new SeatCategory object
////                SeatCategory seatCategory = new SeatCategory();
////                seatCategory.setName(seatCategoryDTO.getName());
////                seatCategory.setCapacity(seatCategoryDTO.getCapacity());
////                seatCategory.setPrice(seatCategoryDTO.getPrice());
////                seatCategory.setScreen(screen);
////
////                // Add the seat category to the set for this screen
////                seatCategories.add(seatCategory);
////            }
////
////            // Set the seat categories for this screen
////            screen.setSeatCategories(seatCategories);
//
//            // Add the screen to the set of screens
//            screens.add(screen);
//        }

        theatre.setScreens(screens);
        theatreRepository.save(theatre);
        return ResponseEntity.ok().body(theatre);
    }
}
