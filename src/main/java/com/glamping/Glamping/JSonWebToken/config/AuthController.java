/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.JSonWebToken.config;

import com.glamping.Glamping.JSonWebToken.config.AuthResponse;
import com.glamping.Glamping.JSonWebToken.config.LoginRequest;
import com.glamping.Glamping.JSonWebToken.config.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/auth")



public class AuthController {
    private  AuthService as;
    @PostMapping(value = "login")
    //Es el objeto que se va a devolver, este response entity va a representar todas las respuestas de https, estados etc
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(as.login(request));
    }
     @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> registrar(@RequestBody RegisterRequest request ){
        return ResponseEntity.ok(as.register(request));
    }
   
}
