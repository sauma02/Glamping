/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package glamping.glamping.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import glamping.glamping.entidades.Imagen;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, Integer> {
    
    
}
