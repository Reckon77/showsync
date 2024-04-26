package com.ticket.booking.showsync.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
    public String test() {
        return "User message";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String test1() {
        return "Admin message";
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/super")
    public String test2() {
        return "Super Admin message";
    }

}
