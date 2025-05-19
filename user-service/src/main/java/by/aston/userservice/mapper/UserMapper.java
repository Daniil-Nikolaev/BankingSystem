package by.aston.userservice.mapper;


import by.aston.userservice.dto.UserDto;
import by.aston.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto convertToDto(User user);

    //User convertToEntity(UserDto userDto);
    default User convertToEntity(UserDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        return user;
    }
}
