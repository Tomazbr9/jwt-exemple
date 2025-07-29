package com.example.demo.dto.user;

public record UserRequestDTO(
        String username,
        String email,
        String password
) {}
