/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author USUARIO
 */
@Configuration
@EnableWebSecurity
public class SeguridadWeb {
    @Bean
    //Se especifica como Bean
    //EL metodo devolver un objeto filterChain
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //Se retorna siempre y cuando 
        http
                //Se desactiva el csrf que es verificacion por tokens que trae por defecto spring security
                .csrf(csrf -> csrf.disable())
                //Se esta indicando que todos pueden tener acceso a la vista general del controlador /
             .authorizeHttpRequests((authRequest) ->
              authRequest
                   .requestMatchers("/**").permitAll()
                      //A todos los request se les pide que autentiquen
                    .anyRequest().authenticated()
                    )
                .formLogin(withDefaults());
                return http.build();
        
        
    }
}
