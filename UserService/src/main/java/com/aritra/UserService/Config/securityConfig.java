package com.aritra.UserService.Config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
@Configuration
@EnableWebSecurity
public class securityConfig {

    @Beangit
    public SecurityFilterChain securityConfirguration(HttpSecurity http) throws Exception {

        http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  //as we need to use our own jwt token
        .and()
        .authorizeHttpRequests()
        .requestMatchers(org.springframework.http.HttpMethod.POST, "/API/AUTH/**").permitAll()  // no security on these two endpoints
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(new JWTWebTokenValidationFilter(), BasicAuthenticationFilter.class)
        .csrf().disable() // csrf is not needed as we are using jwt token
        .cors()
        .configurationSource(new CorsConfigurationSource() {


            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(Arrays.asList(
                    "http://localhost:3000",
                    "http://localhost:3001"
                )); // Allow these origins

                cfg.setAllowedMethods(Collections.singletonList("*")); // Allow all methods
                cfg.setAllowCredentials(true); // Allow credentials
                cfg.setAllowedHeaders(Collections.singletonList("*")); // Allow all headers
                cfg.setExposedHeaders(Arrays.asList("Authorization")); // Expose these headers
                cfg.setMaxAge(3600L);
                return cfg;
            }
        });
        http.httpBasic();
        http.formLogin();
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() ;
}
