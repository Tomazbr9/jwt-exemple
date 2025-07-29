package com.example.demo.controller;

import com.example.demo.dto.user.UserResponseDTO;
import com.example.demo.security.model.UserDetailsImpl;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> userData(@AuthenticationPrincipal UserDetailsImpl userDetails){
        UserResponseDTO user = service.userData(userDetails);
        return ResponseEntity.ok().body(user);

    }
}
