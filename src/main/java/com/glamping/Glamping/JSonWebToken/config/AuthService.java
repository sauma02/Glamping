/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.JSonWebToken.config;

import com.glamping.Glamping.entidades.Usuario;
import com.glamping.Glamping.enumeraciones.Rol;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AuthService {
    public AuthResponse login(LoginRequest request){
        return null;
    }
    public AuthResponse register(RegisterRequest request){
        //Se utiliza el metodo builder para contruir al usuario desde el servicio de autorizacion
        Usuario u1 = User.builder()
                .username(request.getEmail())
                .password(request.getPassword())
                .roles(Rol.USUARIO)
                .build();
                
                
                
    }
}
