/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.servicios;

import com.glamping.Glamping.entidades.Cabania;
import com.glamping.Glamping.exceptions.MiException;
import com.glamping.Glamping.repositorios.CabaniaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author USUARIO
 */
@Repository
public class CabaniaServicio {
    @Autowired
    private CabaniaRepositorio cr;
    @Transactional
    public void crearCabania(String nombre, String imagen, boolean disponibilidad) throws MiException{
        validar(nombre, imagen, disponibilidad);
        Cabania c1 = new Cabania();
        c1.setNombre(nombre);
        c1.setImagen(imagen);
        c1.setDisponibilidad(disponibilidad);
        cr.save(c1);
    }
    public void validar(String nombre, String imagen, Boolean disponibilidad) throws MiException{
        if(nombre.isEmpty() || nombre == null){
            throw new MiException("El nombre no puede ser nulo");
        }
        if(imagen.isEmpty() || imagen == null){
            throw new MiException("La imagen no puede ser nula");
            
        }
        if(disponibilidad == null){
            throw new MiException("La disponibilidad no puede ser nula");
        }
        
    }
}
