package com.internshipmanagementsystem.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // 🔐 Hardcoded Admin Credentials
    public static final String email = "admin@gmail.com";
    public static final String password = "admin123";
    public static final String role = "ADMIN";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ ADD THIS (VERY IMPORTANT)
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {

        http
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth

    
    //  PUBLIC APIs (No Token Required)
    
    .requestMatchers(
        "/api/students/register",
        "/api/students/login",
        "/api/admin/login",
        "/api/mentors/login",
        "/api/coordinators/login"
    ).permitAll()

   
    //  ADMIN ONLY APIs
    
    .requestMatchers("/api/admin/dashboard").hasRole("ADMIN")
    .requestMatchers("/api/admin/users/**").hasRole("ADMIN")

    //  INTERNSHIP APIs
    
    .requestMatchers("/api/internships/create")
        .hasAnyRole("ADMIN", "COORDINATOR")

    .requestMatchers("/api/internships/update/**")
        .hasAnyRole("ADMIN", "COORDINATOR")

    .requestMatchers("/api/internships/delete/**")
        .hasRole("ADMIN")

    
    //  COORDINATOR APIs
    
    .requestMatchers("/api/coordinators/**")
        .hasAnyRole("ADMIN", "COORDINATOR")

    
    // MENTOR APIs
    
    .requestMatchers("/api/mentors/**")
        .hasAnyRole("ADMIN", "MENTOR")

    
    //  STUDENT APIs
    
    .requestMatchers("/api/students/**")
        .hasRole("STUDENT")

    .anyRequest().authenticated()
)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}