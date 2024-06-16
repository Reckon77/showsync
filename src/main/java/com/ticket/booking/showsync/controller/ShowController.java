package com.ticket.booking.showsync.controller;

import com.ticket.booking.showsync.dto.CreateShowDTO;
import com.ticket.booking.showsync.entity.Show;
import com.ticket.booking.showsync.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowController {
    @Autowired
    ShowService showService;
    @PostMapping("/show")
    public ResponseEntity<Show> createShow(@RequestBody CreateShowDTO dto){

        return showService.createShow(dto);
    }
}
