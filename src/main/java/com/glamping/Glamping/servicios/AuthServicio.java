/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.servicios;

import com.glamping.Glamping.entidades.LoginResponseDTO;
import com.glamping.Glamping.entidades.Role;
import com.glamping.Glamping.entidades.Usuario;
import com.glamping.Glamping.repositorios.RoleRepositorio;
import com.glamping.Glamping.repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    private AuthenticationManager aM;
    @Autowired
    private UsuarioRepositorio uR;
    @Autowired
    private RoleRepositorio rR;
    @Autowired
    private PasswordEncoder pE;
    @Autowired
    private TokenServicio tS;
    
    public Usuario registrarUsuario(String username, String password, String email){
        String encodedPassword = pE.encode(password);
        //Cuando se registra el usuario se envia la clave codificada a la base de datos
        //Al igual que tambien se le preestablece por defecto el rol de usuario
        Role userRole = rR.findByAuthority("Usuario").get();
        //Se crea un set y un hashset para añadir el nuevo rol
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return uR.save(new Usuario(1, username, username, new Date()
                , "3242780208", "MED", email, encodedPassword, authorities));
    }
    public LoginResponseDTO login(String username, String password){
        try {
            //Vamos a proceder con la autenticacion
            Authentication auth = aM.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tS.generateJwt(auth);
            //Se genera una string token con el token autorizado
            //Se retorna el login con el username desde el repositorio y el token
            return new LoginResponseDTO(uR.findByUsername(username).get(), token);
            
        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }
     
    }
}
