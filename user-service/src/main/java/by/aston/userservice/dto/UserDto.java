package by.aston.userservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDto(
        @NotNull(message = "Username is required")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        String username,
        @NotNull(message = "Password is required")
        @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
        String password,
        @NotNull(message = "Email is required")
        @Email
        String email
) {}
