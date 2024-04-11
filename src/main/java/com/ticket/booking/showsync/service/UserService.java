package com.ticket.booking.showsync.service;

import com.ticket.booking.showsync.dto.UserDTO;
import com.ticket.booking.showsync.entity.User;
import com.ticket.booking.showsync.exceptions.ExistingUserException;
import com.ticket.booking.showsync.exceptions.RegistrationFailedException;
import com.ticket.booking.showsync.mapper.UserMapper;
import com.ticket.booking.showsync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ResponseEntity<UserDTO> registerUser(UserDTO user){
        if(userRepository.findByUserName(user.getUserName()).isPresent()){
            throw new ExistingUserException("Username : "+user.getUserName()+" already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userObj = UserMapper.INSTANCE.userDTOToUser(user);
        userObj=userRepository.save(userObj);
        if(userObj.getUserId()>0){
            return ResponseEntity.ok().body(user);
        }else{
            throw new RegistrationFailedException("Failed to create user");
        }

    }
}
