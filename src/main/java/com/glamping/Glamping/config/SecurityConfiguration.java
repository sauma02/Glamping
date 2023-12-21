/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Admin
 */
@Configuration
public class SecurityConfiguration {
    //Se establece el securitychain
    //Se crea el metodo con la anotacion bean para la verificacion de usuarios
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    //Esto facilita que el programa acceda al userdetailservice de UsuarioServicio y de esta manera proceder con la verificacion
    public AuthenticationManager authManager(UserDetailsService userDetail){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetail);
        //Se establece el autenticador, con el user detail para que devuelva un manager
        return new ProviderManager(dao);
    }
    @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         return http
                 .csrf(csrf->csrf.disable())
                 //Se establece este authorizeHttpRequests y el auth que apunte a permitir cualquier llamado
                 //De esta manera se podra acceder a la vista general
                 //Ahora se reemplaza con un authenticated para que solo las personas logueadas puedan ingresar
                 .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                 //Se establece el httpBasic para que se logueen por form
                 .httpBasic().and()
                 .build();
     }
}
