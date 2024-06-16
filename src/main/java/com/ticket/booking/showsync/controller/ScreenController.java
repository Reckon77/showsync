package com.ticket.booking.showsync.controller;

import com.ticket.booking.showsync.dto.Slot;
import com.ticket.booking.showsync.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScreenController {
    @Autowired
    ScreenService screenService;

    @GetMapping("/slot/{id}")
    public ResponseEntity<List<Slot>> getSlots(@PathVariable("id") String id){
        return ResponseEntity.ok(screenService.findSlotsById(id));
    }
}
