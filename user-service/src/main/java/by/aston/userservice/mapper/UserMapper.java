package by.aston.userservice.mapper;


import by.aston.userservice.dto.UserDto;
import by.aston.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto convertToDto(User user);

    User convertToEntity(UserDto userDto);
}
