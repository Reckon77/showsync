package com.ticket.booking.showsync.controller;

import com.ticket.booking.showsync.dto.CustomResponseDTO;
import com.ticket.booking.showsync.dto.JwtRequest;
import com.ticket.booking.showsync.dto.JwtResponse;
import com.ticket.booking.showsync.dto.UserDTO;
import com.ticket.booking.showsync.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @DeleteMapping("/test")
    public String test() {
        return "Testing";
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){
        return userService.login(jwtRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteUser")
    public ResponseEntity<CustomResponseDTO> deleteUser(@RequestParam("userName") String userName) {
        return userService.deleteUser(userName);
    }
}
