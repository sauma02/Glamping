/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package glamping.glamping.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import glamping.glamping.entidades.Imagen;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, Integer> {
    Optional<Imagen> getImagenById(Integer id);
    Optional<Imagen> findById(Integer id);
    public Imagen findByInfoId(Integer id);
    public void deleteByCabaniaId(Integer id);
    
}
