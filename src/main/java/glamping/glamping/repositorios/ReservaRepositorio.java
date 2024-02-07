/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package glamping.glamping.repositorios;

import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Reserva;
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
    List<Reserva> findByCabinAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Cabania cab, LocalDate fechaInicial, LocalDate fechaFinal);
}
