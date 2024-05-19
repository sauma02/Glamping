/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Reserva;
import glamping.glamping.entidades.Usuario;
import glamping.glamping.repositorios.CabaniaRepositorio;
import glamping.glamping.repositorios.ReservaRepositorio;
import glamping.glamping.repositorios.UsuarioRepositorio;
import glamping.glamping.servicios.CabaniaServicio;
import glamping.glamping.servicios.EmailServicioImpl;
import glamping.glamping.servicios.ReservaServicio;
import glamping.glamping.servicios.UsuarioServicio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author USUARIO
 */
@Controller
public class ReservaController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ReservaServicio reservaServicio;
    @Autowired
    private ReservaRepositorio reservaRepositorio;
    @Autowired
    private CabaniaServicio cabaniaServicio;
    @Autowired
    private CabaniaRepositorio cabaniaRepositorio;
    @Autowired
    private JavaMailSender javaMail;
    @Autowired
    private EmailServicioImpl emailServicio;

    @GetMapping("/usuario/reservaForm/fechasNoDisponibles/{cabaniaId}")
    @ResponseBody
    public List<String> obtenerFechasNoDispo(@PathVariable("cabaniaId") Integer id) {
        List<Reserva> reservas = reservaServicio.buscarReservaPorCabañaId(id);
        return obtenerFechasNoDisponibles(reservas);

    }
    @GetMapping("/usuario/misReservas/editar/fechasNoDisponibles/{cabaniaId}")
    @ResponseBody
    public List<String> obtenerFechasNoDispo2(@PathVariable("cabaniaId") Integer cabId) {
       
        List<Reserva> reservas = reservaServicio.buscarReservaPorCabañaId(cabId);
        
        return obtenerFechasNoDisponibles(reservas);

    }

    public List<String> obtenerFechasNoDisponibles(List<Reserva> reservas) {
        List<String> fechasNoDisponibles = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Reserva r : reservas) {
            LocalDate fechaInicio = r.getFechaInicio();
            LocalDate fechaFinal = r.getFechaFinal();
            while (!fechaInicio.isAfter(fechaFinal)) {
                fechasNoDisponibles.add(fechaInicio.format(formatter));
                fechaInicio = fechaInicio.plusDays(1);
            }

        }
        return fechasNoDisponibles;
    }

    @GetMapping("/usuario/reservaForm/{id}")
    public List<LocalDate> calendario(@PathVariable Integer id, ModelMap map) {
        Cabania cabania = cabaniaServicio.listarCabaniaPorId(id);
        map.addAttribute("cabania", cabania);
        List<LocalDate> listaFechas = reservaServicio.obtenerFechasReservadasParaCabañaPorId(id);
        return listaFechas;
    }

    @PostMapping("/usuario/reservaForm/reserva")
    public String submitReserva(@ModelAttribute("reserva") Reserva reserva, @RequestParam("nombreUsuario") String nombreUsuario, @RequestParam("cabaniaId") Integer cabaniaId,
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "dd/mm/yy") LocalDate fechaInicio,
            @RequestParam("fechaFinal") @DateTimeFormat(pattern = "dd/mm/yy") LocalDate fechaFinal, ModelMap map) throws Exception {
        try {

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();

            Usuario usuario = usuarioRepositorio.findByUsername(username);
            Optional<Cabania> optionalCabania = cabaniaRepositorio.findById(cabaniaId);

            if (usuario != null && optionalCabania.isPresent()) {
                Cabania cabania = optionalCabania.get();

                List<Reserva> reservas = reservaRepositorio.findByCabaniaAndFechaInicioLessThanEqualAndFechaFinalGreaterThanEqual(cabania, fechaInicio, fechaFinal);
                List<String> fechasNoDisponibles = obtenerFechasNoDisponibles(reservas);

                boolean fechaDisponible = fechasNoDisponibles.isEmpty();

                for (Reserva r : reservas) {
                    if (r.getFechaInicio().isBefore(fechaFinal) && r.getFechaFinal().isAfter(fechaFinal)) {
                        fechaDisponible = false;
                        break;
                    }
                }

                if (fechaDisponible) {
                    String urlWp = "https://api.whatsapp.com/send/?phone=573052126060&text&type=phone_number&app_absent=0";
                    emailServicio.enviarMensajeSencillo(usuario.getEmail(), "Reserva",
                            "Reserva realizada con exito para los dias " + fechaInicio + " - " + fechaFinal + " para terminar la confirmacion de la reserva escribenos a nuestro numero para proceder con el pago. Whatsapp: "+urlWp);
                    map.addAttribute("reservationStatus", "success");
                    map.addAttribute("reservationMessage", "Reserva realizada con éxito, a tu correo recibiras informacion sobre tu reserva");
                    reservaServicio.crearReserva(cabania.getId(), usuario.getId(), usuario.getNombre(), fechaInicio, fechaFinal);

                    return "reservaForm.html";
                } else {
                    String urlWp = "https://api.whatsapp.com/send/?phone=573052126060&text&type=phone_number&app_absent=0";
                    map.addAttribute("fecha_error", "La cabaña no se encuentra disponible en estas fechas: " + fechaInicio + " - " + fechaFinal);
                    map.addAttribute("fechasNoDisponibles", fechasNoDisponibles);
                    map.addAttribute("nombreUsuario", nombreUsuario); // Mantener el nombre de usuario en el formulario
                    map.addAttribute("cabaniasDisponibles", cabaniaRepositorio.findAll()); // Recargar la lista de cabañas disponibles
                     map.addAttribute("reservationStatus", "error");
            map.addAttribute("reservationMessage", "Hubo un error al realizar la reserva");
                    return "reservaForm.html";
                }
            } else {
                throw new IllegalArgumentException("Usuario o cabaña no encontrados");
            }
        } catch (IllegalArgumentException e) {
            // Manejar la excepción de usuario o cabaña no encontrados
            map.addAttribute("fecha_error", e.getMessage());
            map.addAttribute("nombreUsuario", nombreUsuario); // Mantener el nombre de usuario en el formulario
            map.addAttribute("cabaniasDisponibles", cabaniaRepositorio.findAll()); // Recargar la lista de cabañas disponibles
            return "reservaForm.html";
        } catch (Exception e) {
            // Manejar otras excepciones
            map.addAttribute("error_message", "La cabaña no se encuentra disponible en las fechas " + fechaInicio + " a la " + fechaFinal);
            map.addAttribute("nombreUsuario", nombreUsuario); // Mantener el nombre de usuario en el formulario
            map.addAttribute("cabaniasDisponibles", cabaniaRepositorio.findAll()); // Recargar la lista de cabañas disponibles
            return "reservaForm.html";

        }

    }

    @GetMapping("/usuario/misReservas")
    public String misReservas(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        Usuario usuario = usuarioServicio.encontrarPorUsername(username);
        List<Reserva> reservas = reservaServicio.obtenerPorUsuarioId(usuario.getId());
        model.addAttribute("reservas", reservas);

        model.addAttribute("nombreUsuario", username);
        return "misReservas.html";
    }
      @GetMapping("/usuario/misReservas/editar/{id}")
    public String misReservas(@PathVariable Integer id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        Usuario usuario = usuarioServicio.encontrarPorUsername(username);
        List<Reserva> reservas = reservaServicio.obtenerPorUsuarioId(usuario.getId());
        model.addAttribute("reservas", reservas);
        Reserva reserva = reservaServicio.listarReservaPorId(id);
        Cabania cabania = reserva.getCabania();
        List<Cabania> cabanias = cabaniaServicio.listarCabanias();
        model.addAttribute("cabanias", cabanias);
        model.addAttribute("nombreUsuario", username);
        model.addAttribute("reserva", reserva);
        return "editarReserva.html";
    }

    @PostMapping("/usuario/misReservas/editar/editarReserva")
    public String editarReserva( 
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaInicio, 
            @RequestParam("fechaFinal") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaFinal,  ModelMap map, @RequestParam("nombreUsuario") String nombreUsuario, 
            @RequestParam("cabaniaId") Integer cabaniaId, @RequestParam("reservaId") Integer reservaId ) {
        try {
             UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            Reserva reserva = reservaServicio.listarReservaPorId(reservaId);
            Usuario usuario = reserva.getUsuario();
            Cabania cabania = reserva.getCabania();
            
            if (usuario != null && cabania != null) {
                

                List<Reserva> reservas = reservaRepositorio.findByCabaniaAndFechaInicioLessThanEqualAndFechaFinalGreaterThanEqual(cabania, fechaInicio, fechaFinal);
                List<String> fechasNoDisponibles = obtenerFechasNoDisponibles(reservas);
               
                boolean fechaDisponible = fechasNoDisponibles.isEmpty();
                

                for (Reserva r : reservas) {
                    if (r.getFechaInicio().isBefore(fechaFinal) && r.getFechaFinal().isAfter(fechaFinal)) {
                        fechaDisponible = false;
                        break;
                    }
                }
                
                if (fechaDisponible) {
                    Cabania cabaniaNueva = cabaniaServicio.listarCabaniaPorId(cabaniaId);
                    if(cabania != cabaniaNueva){
                         emailServicio.enviarMensajeSencillo(usuario.getEmail(), "Reserva",
                            "Reserva editada con exito, sus nuevos dias son " + fechaInicio + " - " + fechaFinal + " y la cabaña ahora sera la " + cabaniaNueva.getNombre() +
                                     " para terminar la confirmacion de la reserva escribenos a nuestro numero para proceder con el pago");
                    }else{
                        emailServicio.enviarMensajeSencillo(usuario.getEmail(), "Reserva",
                            "Reserva editada con exito, sus nuevos dias son " + fechaInicio + " - " + fechaFinal + " para terminar la confirmacion de la reserva escribenos a nuestro numero para proceder con el pago");
                    map.addAttribute("reservaExito", "Se ha registrado su reserva con exito");
                    }
                    
                    reservaServicio.editarReserva(reserva.getId(), cabaniaId, usuario.getUsername(), fechaInicio, fechaFinal);
                    map.addAttribute("exitoStatus", "success");
               map.addAttribute("exitoMensaje", "Exito al editar reserva");
                    return "editarReserva.html";
                    
                } else {
                    map.addAttribute("fecha_error", "La cabaña no se encuentra disponible en estas fechas: " + fechaInicio + " - " + fechaFinal);
                    map.addAttribute("fechasNoDisponibles", fechasNoDisponibles);
                    map.addAttribute("nombreUsuario", usuario.getUsername()); // Mantener el nombre de usuario en el formulario
                    map.addAttribute("cabaniasDisponibles", cabaniaRepositorio.findAll()); // Recargar la lista de cabañas disponibles
                    return "redirect:/usuario/misReservas/editar/"+reserva.getId();
                }
            } else {
                throw new IllegalArgumentException("Usuario o cabaña no encontrados");
            }
        } catch (IllegalArgumentException e) {
            Reserva reserva = reservaServicio.listarReservaPorId(reservaId);
            // Manejar la excepción de usuario o cabaña no encontrados
            map.addAttribute("fecha_error", e.getMessage());
            map.addAttribute("nombreUsuario", nombreUsuario); // Mantener el nombre de usuario en el formulario
            map.addAttribute("cabaniasDisponibles", cabaniaRepositorio.findAll()); // Recargar la lista de cabañas disponibles
            return "redirect:/usuario/misReservas/editar/"+reserva.getId();
        } catch (Exception e) {
            Reserva reserva = reservaServicio.listarReservaPorId(reservaId);
            // Manejar otras excepciones
            map.addAttribute("error_message", "La cabaña no se encuentra disponible en las fechas " + fechaInicio + " a la " + fechaFinal);
            map.addAttribute("nombreUsuario", nombreUsuario); // Mantener el nombre de usuario en el formulario
            map.addAttribute("cabaniasDisponibles", cabaniaRepositorio.findAll()); // Recargar la lista de cabañas disponibles
            return "redirect:/usuario/misReservas/editar/"+reserva.getId();

        }
      

    }
    @PostMapping("/usuario/misReservas/eliminar/{id}")
    public String eliminarReserva(@PathVariable Integer id){
        try {
            Reserva reserva = reservaServicio.listarReservaPorId(id);
            reservaServicio.eliminarReserva(reserva.getId());
            return "redirect:/usuario/misReservas";
        
        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
            return "error.html";
        }
    }

}
