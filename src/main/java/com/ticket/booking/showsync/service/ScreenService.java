package com.ticket.booking.showsync.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.booking.showsync.dto.Slot;
import com.ticket.booking.showsync.entity.Screen;
import com.ticket.booking.showsync.exceptions.ResourceNotFoundException;
import com.ticket.booking.showsync.repository.ScreenRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ScreenService {

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    ObjectMapper objectMapper;

    public List<Slot> findSlotsById(String screenId)  {

        Optional<Screen> screen= screenRepository.findById(screenId);
        if(screen.isEmpty()){
            throw new ResourceNotFoundException("Unable to fetch screen with Id "+screenId);
        }
        List<Slot> slots= new ArrayList<>();
        try {
            slots =objectMapper.readValue(screen.get().getSlots(), new TypeReference<List<Slot>>(){});
        }
        catch (Exception e){
            log.error("Exception Occurred {}",e.getMessage());
            throw new ResourceNotFoundException("Slots not found");
        }
        return slots;

    }
}
