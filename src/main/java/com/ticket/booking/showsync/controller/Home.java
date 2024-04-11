package com.ticket.booking.showsync.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @RequestMapping("/test")
    public String test() {
        return "Testing message";
    }


}
