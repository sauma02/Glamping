/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package glamping.glamping.repositorios;


import glamping.glamping.entidades.Cabania;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface CabaniaRepositorio extends JpaRepository<Cabania, Integer> {
    public Cabania findFirstById(Integer id);
    public Cabania findFirstByNombre(String nombre);
}
