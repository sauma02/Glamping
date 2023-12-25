/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.servicios;

import com.glamping.Glamping.entidades.Role;
import com.glamping.Glamping.entidades.Usuario;
import com.glamping.Glamping.repositorios.RoleRepositorio;
import com.glamping.Glamping.repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
//Se añade esta anotacion aca para especificar que va a hacer la conexion 
//con la base de datos
public class AuthServicio {
    @Autowired
    private UsuarioRepositorio uR;
    @Autowired
    private RoleRepositorio rR;
    @Autowired
    private PasswordEncoder pE;
    
    public Usuario registrarUsuario(String username, String password, String email){
        String encodedPassword = pE.encode(password);
        //Cuando se registra el usuario se envia la clave codificada a la base de datos
        //Al igual que tambien se le preestablece por defecto el rol de usuario
        Role userRole = rR.findByAuthority("Usuario").get();
        //Se crea un set y un hashset para añadir el nuevo rol
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return uR.save(new Usuario(Integer.SIZE, username, username, new Date()
                , "3242780208", "MED", email, encodedPassword, authorities));
    }
}
