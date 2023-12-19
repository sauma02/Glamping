/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.servicios;

import com.glamping.Glamping.entidades.Usuario;
import com.glamping.Glamping.enumeraciones.Rol;
import com.glamping.Glamping.exceptions.MiException;
import com.glamping.Glamping.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service

public class UsuarioServicio implements UserDetailsService {
    @Autowired
    private UsuarioRepositorio ur;
    @Transactional
    public void crearUsuario(String nombre, String email, 
            String password, String contactoEmergencia, Date fechaNacimiento, 
            String ciudad, Rol rol) throws MiException{
        validar(nombre, email, password, contactoEmergencia, fechaNacimiento,ciudad);
        Usuario u1 = new Usuario();
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
    public void validar(String nombre, String email, 
            String password, String contactoEmergencia, Date fechaNacimiento, 
            String ciudad) throws MiException{
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
