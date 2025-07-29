package com.example.demo.dto.user;

import com.example.demo.enums.RoleName;

public record UserRequestDTO(
        String username,
        String email,
        String password,
        RoleName role
) {}
