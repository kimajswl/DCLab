package com.example.dclab.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests().requestMatchers("/signup", "/login", "/**").permitAll()
                .anyRequest().authenticated();

//        http
//                .authorizeRequests()
//                .anyRequest().authenticated();
//
//        http.formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .failureUrl("/")
//                .usernameParameter("userId")
//                .passwordParameter("password")
//                .loginProcessingUrl("login")
//                .permitAll();

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
