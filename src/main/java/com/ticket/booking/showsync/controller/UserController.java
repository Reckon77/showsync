package com.ticket.booking.showsync.controller;

import com.ticket.booking.showsync.dto.JwtRequest;
import com.ticket.booking.showsync.dto.JwtResponse;
import com.ticket.booking.showsync.dto.UserDTO;
import com.ticket.booking.showsync.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Register a new user or admin")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO user) {
        return userService.registerUser(user);
    }
    @Operation(summary = "User login")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){
        return userService.login(jwtRequest);
    }
}
