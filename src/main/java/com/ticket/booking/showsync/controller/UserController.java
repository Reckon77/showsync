package com.ticket.booking.showsync.controller;

import com.ticket.booking.showsync.dto.CustomResponseDTO;
import com.ticket.booking.showsync.dto.JwtRequest;
import com.ticket.booking.showsync.dto.JwtResponse;
import com.ticket.booking.showsync.dto.UserDTO;
import com.ticket.booking.showsync.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
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

    @DeleteMapping("/deleteUser/{userName}")
    public ResponseEntity<CustomResponseDTO> deleteUser(@PathVariable String userName, Authentication authentication) {
        String user = authentication.getName();
        if(user.equals(userName)) {
            return userService.deleteUser(userName);
        }
        throw new BadCredentialsException("Unauthorized to delete " + userName);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<CustomResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO, Authentication authentication) {
        String user = authentication.getName();
        if(user.equals(userDTO.getUserName())) {
            return userService.updateUser(id, userDTO);
        }
        throw new BadCredentialsException("Unauthorized to update " + user);
    }
}
