/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.controladores;

import com.glamping.Glamping.enumeraciones.Rol;
import com.glamping.Glamping.exceptions.MiException;
import com.glamping.Glamping.repositorios.UsuarioRepositorio;
import com.glamping.Glamping.servicios.UsuarioServicio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author USUARIO
 */
@Controller
//Se utiliza requestmapping annotation("/") de esta manera para dar la vista general
@RequestMapping("/")
public class PortalControlador {
    @Autowired
    private UsuarioRepositorio ur;
     @Autowired
    private UsuarioServicio us;
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
   
    @GetMapping("/inicio")
    public String inicio(){
        return "index.html";
    }
    @GetMapping("/registro")
    public String registro(){
        return "registrar.html";
    }
    //Se utiliza el metodo el postMapping para luego utilizarlo en el form
    @PostMapping("/registrar")
    public String registrarUsuario(@RequestParam String nombre, @RequestParam String email,
                        @RequestParam String password, @RequestParam String contactoEmergencia,
                                @RequestParam Date fechaNacimiento, @RequestParam String ciudad, ModelMap modelo){
        try {
            us.crearUsuario(nombre, email, password, contactoEmergencia, fechaNacimiento, ciudad, Rol.USUARIO);
            modelo.put("Exito", "Usuario registrado con exito");
            return "index.html";
            
        } catch (MiException ex) {
            modelo.put("Error", ex);
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("contactoEmergencia", contactoEmergencia);
            modelo.put("fechaNacimiento", fechaNacimiento);
            modelo.put("ciudad", ciudad);
            return "registrar.html";
        }
    }
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        if(error != null){
            modelo.put("Error", "Usuario o contraseña invalida");
        }
            return "login.html";
        
    }
}
