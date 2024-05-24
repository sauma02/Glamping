/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package glamping.glamping.repositorios;

import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Reserva;
import glamping.glamping.entidades.Usuario;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByCabaniaAndFechaInicioLessThanEqualAndFechaFinalGreaterThanEqual(Cabania cabania, LocalDate fechaInicio, LocalDate fechaFinal);
    public Reserva findByUsuario(Usuario usuario);
    public List<Reserva> findByCabania(Cabania cabania);
    public List<Reserva> findByCabaniaId(Integer id);
    public List<Reserva> findByUsuarioId(Integer id);
    public void deleteByCabaniaId(Integer id);
}
