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
    public void crearReserva(Integer cabaniaId, Integer usuarioId,String nombre,Date fecha){
           Optional<Usuario> respuesta = usuarioRepositorio.findById(usuarioId);
           Optional<Cabania> respuesta2 = cabaniaRepositorio.findById(cabaniaId);
           if(respuesta.isPresent() && respuesta2.isPresent()){
              Usuario usuario = respuesta.get();
              Cabania cabania = respuesta2.get();
              List<Cabania> cabaniaLista = new ArrayList();
              List<Usuario> usuarioLista = new ArrayList();
              usuarioLista.add(usuario);
              cabaniaLista.add(cabania);
               Reserva reserva = new Reserva();
               reserva.setNombre(nombre);
               reserva.setUsuario(usuarioLista);
               reserva.setCabania(cabaniaLista);
               reserva.setFecha(fecha);
               reservaRepositorio.save(reserva);
           }
    }
    public void editarReserva(Integer id, Integer cabaniaId, String nombre,Date fecha){
        Optional<Reserva> respuesta = reservaRepositorio.findById(id);
        Optional<Cabania> respuesta2 = cabaniaRepositorio.findById(cabaniaId);
        if(respuesta.isPresent()){
            Cabania cabania = respuesta2.get();
            List<Cabania> cabaniaLista = new ArrayList();
              cabaniaLista.add(cabania);
            Reserva reserva = respuesta.get();
            reserva.setNombre(nombre);
            reserva.setCabania(cabaniaLista);
            reserva.setFecha(fecha);
            
            
        }
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
