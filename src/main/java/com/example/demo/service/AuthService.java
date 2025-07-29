package com.example.demo.service;

import com.example.demo.dto.auth.JwtTokenDTO;
import com.example.demo.dto.auth.LoginDTO;
import com.example.demo.dto.user.UserRequestDTO;
import com.example.demo.model.RoleModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SecurityConfiguration;
import com.example.demo.security.jwt.JwtTokenService;
import com.example.demo.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public void registerUser(UserRequestDTO request){

        RoleModel role = roleRepository.findByName(request.role()).orElseThrow(() -> new RuntimeException("Papel não encontrado"));

        UserModel user = UserModel.builder()
                .username(request.username())
                .email(request.email())
                .password(securityConfiguration.passwordEncoder().encode(request.password()))
                .roles(List.of(role))
                .build();

        userRepository.save(user);
    }

    public JwtTokenDTO authenticateUser(LoginDTO request){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new JwtTokenDTO(jwtTokenService.generateToken(userDetails));
    }

}
