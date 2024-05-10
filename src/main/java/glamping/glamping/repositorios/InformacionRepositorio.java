/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package glamping.glamping.repositorios;

import glamping.glamping.entidades.Informacion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface InformacionRepositorio extends JpaRepository<Informacion, Integer> {
    Optional<Informacion> findById(Integer id);
    public List<Informacion> findAll();
}
