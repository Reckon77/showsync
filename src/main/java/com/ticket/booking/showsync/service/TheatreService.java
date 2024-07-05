package com.ticket.booking.showsync.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.booking.showsync.dto.CreateTheatreDTO;
import com.ticket.booking.showsync.dto.Slot;
import com.ticket.booking.showsync.entity.*;
import com.ticket.booking.showsync.exceptions.LocationNotFoundException;
import com.ticket.booking.showsync.repository.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TheatreService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private SeatCategoryRepository seatCategoryRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<Theatre> createTheatre(CreateTheatreDTO createTheatreDTO,String userName){
        Optional<User> user = userRepository.findByUserName(userName);
        User userObj=null;
        if(user.isPresent()){
            userObj=user.get();
        }else {
            throw new UsernameNotFoundException("User not found");
        }
        // check if the city is present in location table if not throw exception
        String city=createTheatreDTO.getAddress().getCity();
        Optional<Location> location = locationRepository.findByLocationName(city);
        if(location.isEmpty()){
            throw new LocationNotFoundException(city+ " not found in the location database");
        }
        val locationObj = location.get();
        Theatre theatre = Theatre.builder()
                .name(createTheatreDTO.getName())
                .city(city)
                .longitide(createTheatreDTO.getAddress().getLongitude())
                .latitude(createTheatreDTO.getAddress().getLatitude())
                .pincode(createTheatreDTO.getAddress().getPincode())
                .address(createTheatreDTO.getAddress().getAddress())
                .build();
        theatre.setLocation(locationObj);
        theatre.setUser(userObj);
        List<Screen> screens = createTheatreDTO.getScreens().stream()
                .map(screenDTO -> {
                    Screen screen = new Screen();
                    screen.setName(screenDTO.getName());
                    screen.setSlots(convertToJsonString(screenDTO.getSlots()));
                    screen.setTheatre(theatre);
                    List<SeatCategory> seatCategories = screenDTO.getSeatCategories().stream()
                            .map(seatCategoryDTO -> {
                                SeatCategory seatCategory = new SeatCategory();
                                seatCategory.setName(seatCategoryDTO.getName());
                                seatCategory.setCapacity(seatCategoryDTO.getCapacity());
                                seatCategory.setPrice(seatCategoryDTO.getPrice());
                                seatCategory.setScreen(screen);
                                return seatCategory;
                            }).toList();
                    screen.setSeatCategories(seatCategories);
                    return  screen;
                }).toList();
        theatre.setScreens(screens);
        theatreRepository.save(theatre);
        return ResponseEntity.ok().body(theatre);
    }



    public Optional<Theatre> getTheatre(String theatreName, String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        User userObj=null;
        if(user.isPresent()){
            userObj=user.get();
        }else {
            throw new UsernameNotFoundException("User not found");
        }
        return theatreRepository.findByName(theatreName);
    }

    public Optional<List<Theatre>> getAllTheatre(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        User userObj = null;
        if(user.isPresent()){
            userObj=user.get();
        }
        else{
            throw new UsernameNotFoundException("User not found");
        }

        return Optional.of(theatreRepository.findAllByUser(userObj));
    }

//    public List<Theatre> getTheatreByLocation(String locationId) {
//        Optional<List<Theatre>> theatres = theatreRepository.findByLocation(locationId);
//        return theatres.orElse(Collections.emptyList());
//    }
    public ResponseEntity<List<Theatre>> getAllTheatreByLocation(String id){
        Optional<Location> location = locationRepository.findByLocationId(id);
        if(location.isEmpty()){
            throw new LocationNotFoundException(id+ " location not found in the location database");
        }
        Optional<List<Theatre>> theatres = theatreRepository.findByLocation_LocationId(id);
        return ResponseEntity.ok().body(theatres.get());
    }
    private String convertToJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error while converting Object to Json String {}",e.getMessage());
            return null;
        }
    }

}
