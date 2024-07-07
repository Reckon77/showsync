package com.ticket.booking.showsync.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String locationId;
    @Column(unique = true)
    @NotBlank(message = "Location name is required")
    private String locationName;
    //@Pattern(regexp = "\\d{6}", message = "Pin code must be 6 digits")
    //@NotBlank(message = "Pin Code is required")
    private int pinCode;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "location")
    List<Theatre> theatres = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch =FetchType.LAZY,mappedBy = "location")
    List<User> users = new ArrayList<>();
}
