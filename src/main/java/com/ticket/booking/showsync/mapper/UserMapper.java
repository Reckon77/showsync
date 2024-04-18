package com.ticket.booking.showsync.mapper;
import com.ticket.booking.showsync.dto.UserDTO;
import com.ticket.booking.showsync.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "userId", ignore = true) // Ignore userId as it's auto-generated
    @Mapping(target = "dateOfBirth", source = "dto.dateOfBirth")
    public abstract User userDTOToUser(UserDTO dto);

    //UserDTO userToUserDTO(User user);
}
