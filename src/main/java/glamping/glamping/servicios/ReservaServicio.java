/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;

import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Reserva;

import glamping.glamping.entidades.Usuario;
import glamping.glamping.repositorios.CabaniaRepositorio;
import glamping.glamping.repositorios.ReservaRepositorio;
import glamping.glamping.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ReservaServicio {
    @Autowired
    private ReservaRepositorio reservaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private CabaniaRepositorio cabaniaRepositorio;
    @Transactional
    public void crearReserva(Integer cabaniaId, Integer usuarioId,String nombre,LocalDate startDate, LocalDate endDate ){
           Optional<Usuario> respuesta = usuarioRepositorio.findById(usuarioId);
           Optional<Cabania> respuesta2 = cabaniaRepositorio.findById(cabaniaId);
           
           if(respuesta.isPresent() && respuesta2.isPresent()){
              Usuario usuario = respuesta.get();
              Cabania cabania = respuesta2.get();
              boolean disponible = isCabinAvailable(cabania, startDate, endDate);
              if(!disponible){
                  throw new RuntimeException("La cabaña no esta disponible en esas fechas");
              }else{
              
               Reserva reserva = new Reserva();
               reserva.setNombre(nombre);
               reserva.setUsuario(usuario);
               reserva.setCabania(cabania);
               reserva.setFechaInicio(startDate);
               reserva.setFechaFinal(endDate);
               reservaRepositorio.save(reserva);
              }
           }
    }
    public void editarReserva(Integer id, Integer cabaniaId, String nombre,LocalDate startDate, LocalDate endDate){
        Optional<Reserva> respuesta = reservaRepositorio.findById(id);
        Optional<Cabania> respuesta2 = cabaniaRepositorio.findById(cabaniaId);
        if(respuesta.isPresent()){
            Cabania cabania = respuesta2.get();
             boolean disponible = isCabinAvailable(cabania, startDate, endDate);
              if(!disponible){
                  throw new RuntimeException("La cabaña no esta disponible");
              }else{
      
            Reserva reserva = respuesta.get();
            reserva.setNombre(nombre);
            reserva.setCabania(cabania);
            reserva.setFechaInicio(startDate);
            reserva.setFechaFinal(endDate);
            reservaRepositorio.save(reserva);
              }
            
        }
    }
     public boolean isCabinAvailable(Cabania cabin, LocalDate startDate, LocalDate endDate) {
        List<Reserva> overlappingReservations = reservaRepositorio.findByCabaniaAndFechaInicioLessThanEqualAndFechaFinalGreaterThanEqual(cabin, endDate, endDate);
        return overlappingReservations.isEmpty();
    }
    public Reserva listarReservaPorId(Integer id){
        Optional<Reserva> respuesta = reservaRepositorio.findById(id);
        if(respuesta.isPresent()){
            Reserva reserva = respuesta.get();
            return reserva;
        }else{
            return null;
        }
    }
    public void eliminarReserva(Integer id){
        Optional<Reserva> respuesta = reservaRepositorio.findById(id);
        if(respuesta.isPresent()){
            Reserva reserva = respuesta.get();
            reservaRepositorio.delete(reserva);
            
        }
    }
    public List<Reserva> listarReservas(){
        return reservaRepositorio.findAll();
    }
}
