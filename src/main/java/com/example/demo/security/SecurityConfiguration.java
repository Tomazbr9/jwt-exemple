package com.example.demo.security;

import com.example.demo.security.filter.UserAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Ativa as configurações de segurança do Spring Security
public class SecurityConfiguration {

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    // Endpoints que não exigem autenticação
    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/auth/register",
            "/auth/login"
    };

    // Endpoints que exigem perfil de Usuário
    public static final String[] ENDPOINTS_USER = {
            "users/user/me"
    };

    // Endpoints que exigem perfil de ADMIN
    public static final String[] ENDPOINTS_ADMIN = {
            "users/admin/me"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF para APIs REST (sem sessões)

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // API sem uso de sessões
                )

                .authorizeHttpRequests(auth -> auth
                        // Libera acesso público para os endpoints sem autenticação
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()

                        // Restringe acesso aos endpoints CUSTOMER
                        .requestMatchers(ENDPOINTS_USER).hasRole("USER")

                        // Restringe acesso aos endpoints ADMIN
                        .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMIN")

                        // Qualquer outra rota exige autenticação
                        .anyRequest().authenticated()
                )

                // Adiciona o filtro de autenticação JWT antes do filtro padrão do Spring
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Permite usar o AuthenticationManager padrão do Spring para autenticação
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Utiliza o algoritmo BCrypt para codificar senhas
        return new BCryptPasswordEncoder();
    }
}

