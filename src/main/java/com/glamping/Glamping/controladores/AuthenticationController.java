/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.controladores;

import com.glamping.Glamping.entidades.RegistrationDTO;
import com.glamping.Glamping.entidades.Usuario;
import com.glamping.Glamping.servicios.AuthServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author laura
 */
@RestController
//Esta anotacion nos permitiria establecer punto de mapa en el backend del servidor
@RequestMapping("/auth")
//Se establece esta anotacion para el mapeo de la url
@CrossOrigin("*")
//Para esteblecer los accesos
public class AuthenticationController {
    
    @Autowired 
    //Para que automaticamente lo inyecte al iniciar
    private AuthServicio aS;
    
    @PostMapping("/register")
    //Se pone como paramaetro un body, en este caso nuestro DTO
    public Usuario registrarUsuario(@RequestBody RegistrationDTO body){
        return aS.registrarUsuario(body.getUsername(), body.getPassword(), body.getEmail());
    }
    
    
}
