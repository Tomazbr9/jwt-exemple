package com.example.demo.service;

import com.example.demo.dto.user.UserResponseDTO;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO userData(UserDetailsImpl userDetails){

        UserModel user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UserResponseDTO(user.getUsername(), user.getEmail());


    }
}
