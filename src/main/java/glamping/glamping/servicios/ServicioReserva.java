/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package glamping.glamping.servicios;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ServicioReserva {
    List<LocalDate> obtenerFechasReservadasParaCabañaPorId(Integer id);
}
