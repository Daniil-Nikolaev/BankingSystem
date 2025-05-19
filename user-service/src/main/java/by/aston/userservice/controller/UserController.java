package by.aston.userservice.controller;

import by.aston.userservice.dto.UserDto;
import by.aston.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userSerivce;

    @Autowired
    public UserController(UserService userSerivce) {
        this.userSerivce = userSerivce;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> createAccount(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        userSerivce.signUp(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        String token = userSerivce.login(userDto);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
