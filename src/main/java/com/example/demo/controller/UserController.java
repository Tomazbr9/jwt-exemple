package com.example.demo.controller;

import com.example.demo.dto.user.UserResponseDTO;
import com.example.demo.security.model.UserDetailsImpl;
import com.example.demo.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> userData(@AuthenticationPrincipal String username){
        UserResponseDTO user = service.userData(username);
        return ResponseEntity.ok().body(user);

    }
}
