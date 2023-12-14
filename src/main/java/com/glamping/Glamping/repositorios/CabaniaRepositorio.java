/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.glamping.Glamping.repositorios;

import com.glamping.Glamping.entidades.Cabania;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author USUARIO
 */
@Repository
public interface CabaniaRepositorio extends JpaRepository<Cabania, String> {
    //@Query("UPDATE estado SET disponibilidad = 'inactiva' WHERE id = :id;")
    //public void desactivarCabania(@Param("id") String id);
        
    
}
