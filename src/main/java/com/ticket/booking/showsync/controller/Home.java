package com.ticket.booking.showsync.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/user")
    public String test() {
        return "User message";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/admin")
    public String test1() {
        return "Admin message";
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping("/super")
    public String test2() {
        return "Super Admin message";
    }

}
