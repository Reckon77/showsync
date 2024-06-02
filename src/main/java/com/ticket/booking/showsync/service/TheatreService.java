package com.ticket.booking.showsync.service;

import com.ticket.booking.showsync.dto.CreateTheatreDTO;
import com.ticket.booking.showsync.entity.Screen;
import com.ticket.booking.showsync.entity.SeatCategory;
import com.ticket.booking.showsync.entity.Theatre;
import com.ticket.booking.showsync.entity.User;
import com.ticket.booking.showsync.repository.ScreenRepository;
import com.ticket.booking.showsync.repository.SeatCategoryRepository;
import com.ticket.booking.showsync.repository.TheatreRepository;
import com.ticket.booking.showsync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private SeatCategoryRepository seatCategoryRepository;

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

        theatre.setUser(userObj);
        List<Screen> screens = createTheatreDTO.getScreens().stream()
                .map(screenDTO -> {
                    Screen screen = new Screen();
                    screen.setName(screenDTO.getName());
                    // TODO : find a better approach
                    screen.setTheatre(theatre);
                    screenRepository.save(screen);
                    screen.setTheatre(null);
                    List<SeatCategory> seatCategories = screenDTO.getSeatCategories().stream()
                            .map(seatCategoryDTO -> {
                                SeatCategory seatCategory = new SeatCategory();
                                seatCategory.setName(seatCategoryDTO.getName());
                                seatCategory.setCapacity(seatCategoryDTO.getCapacity());
                                seatCategory.setPrice(seatCategoryDTO.getPrice());
                                seatCategory.setScreen(screen);
                                seatCategoryRepository.save(seatCategory);
                                seatCategory.setScreen(null);
                                return seatCategory;
                            }).toList();
                    screen.setSeatCategories(new HashSet<>(seatCategories));
                    return  screen;
                }).toList();
        theatre.setScreens(new HashSet<>(screens));
        theatreRepository.save(theatre);
        return ResponseEntity.ok().body(theatre);
    }

    public List<Theatre> getTheatreByLocation(String locationId) {
        return theatreRepository.findByLocationId(locationId);
    }
}
