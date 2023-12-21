/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.servicios;

import com.glamping.Glamping.entidades.Role;
import com.glamping.Glamping.entidades.Usuario;
import com.glamping.Glamping.enumeraciones.Rol;
import com.glamping.Glamping.exceptions.MiException;
import com.glamping.Glamping.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service

public class UsuarioServicio implements UserDetailsService {
    @Autowired
    private UsuarioRepositorio ur;
    @Autowired
    private PasswordEncoder encoder;
    @Transactional
    public void crearUsuario(String username, String nombre, String email, 
            String password, String contactoEmergencia, Date fechaNacimiento, 
            String ciudad, Rol rol) throws MiException{
        validar(username, nombre, email, password, contactoEmergencia, fechaNacimiento,ciudad);
        Usuario u1 = new Usuario();
        u1.setUsername(username);
        u1.setNombre(nombre);
        u1.setEmail(email);
        u1.setPassword(password);
        u1.setFechaNacimiento(fechaNacimiento);
        u1.setCiudad(ciudad);
        u1.setContactoEmergencia(contactoEmergencia);
       
        ur.save(u1);
    }
    public Usuario buscarUsuarioPorId(String id){
        Optional<Usuario> res = ur.findById(id);
        if(res.isPresent()){
            Usuario u1 = res.get();
            return u1;
        }else{
            return null;
        }
    }
    public void eliminarUsuario(String id){
        Optional<Usuario> res = ur.findById(id);
        if(res.isPresent()){
            Usuario u1 = res.get();
            ur.delete(u1);
        }
    }
    public void editarUsuario(String id, String nombre, String email, 
            String password, String contactoEmergencia, Date fechaNacimiento, 
            String ciudad ){
        Optional<Usuario> res = ur.findById(id);
        if(res.isPresent()){
            Usuario u1 = res.get();
                    u1.setNombre(nombre);
                    u1.setEmail(email);
                    u1.setPassword(password);
                    u1.setFechaNacimiento(fechaNacimiento);
                    u1.setCiudad(ciudad);
                    u1.setContactoEmergencia(contactoEmergencia);
                    ur.save(u1);
        }
    }
    public void validar(String username, String nombre, String email, 
            String password, String contactoEmergencia, Date fechaNacimiento, 
            String ciudad) throws MiException{
        if(username.isEmpty()){
            throw new MiException("El username no puede estar vacio");
        }
        if(nombre.isEmpty() || nombre == null){
            throw new MiException("El nombre no puede ser nulo");
        }
        if(email.isEmpty() || email == null){
            throw new MiException("El email no puede ser nulo");

        }
        if(password.isEmpty() || password == null){
            throw new MiException("El password no puede ser nulo");

        }
        if(contactoEmergencia.isEmpty() || contactoEmergencia == null){
            throw new MiException("El contactoEmergencia no puede ser nulo");

        }
        if(fechaNacimiento == null){
            throw new MiException("El fechaNacimiento no puede ser nulo");

        }
        if(ciudad.isEmpty() || ciudad == null){
            throw new MiException("El ciudad no puede ser nulo");

        }
    }
   

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("En el servicio del cliente");
        //se identifica que el username sea igual al ingresado sino que muestre una excepcion
        //Si es, devolver al user
    //        if(!username.equals("Ethan")) throw new UsernameNotFoundException("Error el nombre de usuario no existe");
    //        Set<Role> roles = new HashSet<>();
    //        roles.add(new Role(1, "Usuario"));
    //        return new Usuario("1", "Ethan", "Jose", new Date(), "00", "med", "asdsada@gmail.com", encoder.encode("password"), roles);
    //Se usa un orElseThrow, si esta lo retorna sino muestra la excepcion
    //Dentro del orElseThrow se utiliza un Lambda que se inicia con () para construir la excepcion
            return ur.findByUsername(username).orElseThrow( () -> new UsernameNotFoundException("Usuario no valido"));
    }
}
