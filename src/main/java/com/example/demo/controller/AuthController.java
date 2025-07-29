package com.example.demo.controller;

import com.example.demo.dto.auth.JwtTokenDTO;
import com.example.demo.dto.auth.LoginDTO;
import com.example.demo.dto.user.UserRequestDTO;
import com.example.demo.dto.user.UserResponseDTO;
import com.example.demo.model.UserModel;
import com.example.demo.security.model.UserDetailsImpl;
import com.example.demo.service.AuthService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDTO request){
        service.registerUser(request);
        return new ResponseEntity<>("Usuário Criado com sucesso!",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> authenticateUser(@RequestBody LoginDTO request){
        JwtTokenDTO token = service.authenticateUser(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
