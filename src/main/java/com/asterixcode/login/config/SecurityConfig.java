package com.asterixcode.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.formLogin(
            formLogin ->
                formLogin
                    .loginPage("/login")
                    .permitAll()
//                    .loginProcessingUrl("/authenticate")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error"))
        .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"))
        .authorizeHttpRequests(
            authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/images/**").permitAll() // white list images
                    .anyRequest().authenticated()) // all other requests require authentication
        .build();
  }
}
