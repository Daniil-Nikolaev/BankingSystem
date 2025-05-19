package by.aston.userservice.service;

import by.aston.userservice.dto.UserDto;
import by.aston.userservice.entity.User;
import by.aston.userservice.mapper.UserMapper;
import by.aston.userservice.repository.UserRepository;
import by.aston.userservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    public void signUp(UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        user.setId(UUID.randomUUID());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public String login(UserDto userDto) {
        User currentUser = userMapper.convertToEntity(userDto);
        Optional<User> dbUser = userRepository.findUserByUsername(currentUser.getUsername());

        if (dbUser.isEmpty()) {
            return "User not found";
        }

        String rawPassword = currentUser.getPassword();
        String encodedPassword = dbUser.get().getPassword();

        if (passwordEncoder.matches(rawPassword, encodedPassword)) {
            return jwtUtil.generateToken(dbUser.get().getId());
        } else {
            return "Invalid password";
        }
    }

}
