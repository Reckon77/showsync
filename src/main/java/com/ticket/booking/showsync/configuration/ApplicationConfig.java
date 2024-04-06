package com.ticket.booking.showsync.configuration;

import com.ticket.booking.showsync.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;
    @Bean
    public UserDetailsService userDetailsService(){
       return username -> userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("USER NOT FOUND"));
    }
}
