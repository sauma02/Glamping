/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Admin
 */
@Configuration
public class SecurityConfiguration {
    //Se establece el securitychain
    @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         return http
                 .csrf(csrf->csrf.disable())
                 //Se establece este authorizeHttpRequests y el auth que apunte a permitir cualquier llamado
                 //De esta manera se podra acceder a la vista general
                 .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                 .build();
     }
}
