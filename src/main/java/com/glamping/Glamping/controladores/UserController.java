/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/inicio")
@CrossOrigin("*")
public class UserController {
    
    @GetMapping("/")
    public String holaUserControlador(){
        return "Usuario LEVEL";
    }
    
}
