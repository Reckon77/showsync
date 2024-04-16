package com.ticket.booking.showsync.service;

import com.ticket.booking.showsync.config.JwtService;
import com.ticket.booking.showsync.dto.JwtRequest;
import com.ticket.booking.showsync.dto.JwtResponse;
import com.ticket.booking.showsync.dto.UserDTO;
import com.ticket.booking.showsync.entity.User;
import com.ticket.booking.showsync.exceptions.ExistingUserException;
import com.ticket.booking.showsync.exceptions.RegistrationFailedException;
import com.ticket.booking.showsync.mapper.UserMapper;
import com.ticket.booking.showsync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtService helper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ResponseEntity<UserDTO> registerUser(UserDTO user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new ExistingUserException("Username : " + user.getUserName() + " already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userObj = UserMapper.INSTANCE.userDTOToUser(user);
        userObj = userRepository.save(userObj);
        if (userObj.getUserId() > 0) {
            return ResponseEntity.ok().body(user);
        } else {
            throw new RegistrationFailedException("Failed to create user");
        }

    }


    public ResponseEntity<JwtResponse> login(JwtRequest request) {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = helper.generateToken(request.getUserName());
            JwtResponse response = JwtResponse.builder()
                    .jwtToken(token)
                    .userName(request.getUserName()).build();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
    }
}
