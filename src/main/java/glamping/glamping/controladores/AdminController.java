/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.config.FileUploadUtil;
import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Imagen;
import glamping.glamping.entidades.Reserva;

import glamping.glamping.entidades.ResponseMessage;
import glamping.glamping.entidades.Roles;
import glamping.glamping.entidades.Usuario;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.servicios.CabaniaServicio;
import glamping.glamping.servicios.FileStorageService;
import glamping.glamping.servicios.ImagenServicio;
import glamping.glamping.servicios.ReservaServicio;
import glamping.glamping.servicios.RolesServicio;
import glamping.glamping.servicios.UsuarioServicio;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author USUARIO
 */
@Controller
public class AdminController {

    @Autowired
    private CabaniaServicio cabaniaServicio;
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private ImagenServicio imagenServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ReservaServicio reservaServicio;
    @Autowired
    private RolesServicio rolServicio;

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();

        model.addAttribute("nombreUsuario", username);

        return "admin.html";
    }



    @GetMapping("/admin/registrarCabañas")
    public String registrarCabania(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();

        model.addAttribute("nombreUsuario", username);
        return "registrarCabania.html";
    }

    @PostMapping("/admin/registrarCabañas/registrarCabaña")
    public String registerForm(@RequestParam("nombre") String nombre,
            @RequestParam("capacidad") Integer capacidad, @RequestParam("imagen") MultipartFile imagen, @RequestParam("estado") boolean estado, Model map) throws MiExcepcion, IOException, Exception {
        String mensaje = "";
        try {
            Cabania cab = cabaniaServicio.listarCabaniaPorNombre(nombre);
            if (cab != null) {
                map.addAttribute("errorCabaniaExistente", "La cabaña con el nombre " + nombre + " ya existe");
                map.addAttribute("estado", estado);
                map.addAttribute("capacidad", capacidad);
                mensaje = "la cabaña indicada ya existe";
                return "registrarCabania.html";
            }
            if (imagen == null || imagen.isEmpty()) {
                map.addAttribute("imagenVacia", "Imagen nula");
                throw new Exception("Error, la imagen es nula, no puede ser nula");

            } else {
                // Manejar la carga de la imagen y guardarla
                Cabania cabania = new Cabania();
                Imagen img = new Imagen();
                storageService.init();

                // Establecer los demás atributos de la cabaña
                cabania.setNombre(nombre);
                cabania.setCapacidad(capacidad);
                cabania.setEstado(estado);

                storageService.save(imagen);
                img.setFileName(storageService.listOneFile(imagen).getOriginalFilename());
                // Asignar la imagen a la lista de imágenes de la cabaña
                List<Imagen> listaImagenes = new ArrayList<>();
                listaImagenes.add(img);
                cabania.setImagen(listaImagenes);

                // Guardar la cabaña en la base de datos
                cabaniaServicio.crearCabania(cabania);
                imagenServicio.guardarImagen(cabania, img, imagen);

                // Informar sobre el éxito del registro
                map.addAttribute("exitoCabania", "Éxito al crear cabaña");
                mensaje = "admin.html";
                return mensaje;
            }

        } catch (SQLException e) {
            throw new Exception("Error en la creacion de la cabaña", e.getCause());

        }
    }

    @GetMapping("/admin/verCabañas")
    public String listarCabanias(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        List<Cabania> listaCabanias = cabaniaServicio.listarCabanias();
        for (Cabania cab : listaCabanias) {
            model.addAttribute("cabania", cab);
        }
        model.addAttribute("listaCabanias", listaCabanias);
        model.addAttribute("nombreUsuario", username);
        return "listarCabañas.html";
    }

    @GetMapping("/admin/cabania/editarCabaña/{id}")
    public String editarCabana(@AuthenticationPrincipal UserDetails userDetails, Model model, @PathVariable Integer id) {
        Cabania cabania = cabaniaServicio.listarCabaniaPorId(id);
        model.addAttribute("cabana", cabania);
        return "editarCabana.html";
    }
    @GetMapping("/admin/cabania/añadirImagenes/{id}")
    public String editarImagenes(@AuthenticationPrincipal UserDetails userDetails, Model model, @PathVariable Integer id){
        Cabania cabania = cabaniaServicio.listarCabaniaPorId(id);
        List<Imagen> img = cabania.getImagen();
         model.addAttribute("cabana", cabania);
        model.addAttribute("imagenes", img);
        return "añadirImagenes.html";
    }
    @PostMapping("/admin/cabania/añadirImagenes/añadir")
    public String añadirImagenesForm(@ModelAttribute Cabania cabania, @ModelAttribute Imagen img, MultipartFile imagen){
        try {
            cabaniaServicio.añadirImagenes(cabania, img, imagen);
            return "redirect:/admin/cabania/añadirImagenes/{id}";
            
        } catch (Exception e) {
            e.printStackTrace();
            
            if(e.getCause() != null){
                System.err.println("Error: "+e.getCause().getMessage());
            }
            return "error.html";
        }
    }
    @PostMapping("/admin/cabania/añadirImagenes/eliminar")
    public String eliminarImagenesForm(@ModelAttribute Cabania cabania, Integer id){
        try {
            cabaniaServicio.eliminarImagen(cabania, id);
              return "redirect:/admin/cabania/añadirImagenes/{id}";
        } catch (Exception e) {
             e.printStackTrace();
            
            if(e.getCause() != null){
                System.err.println("Error: "+e.getCause().getMessage());
            }
            return "error.html";
            
        }
    }

    @PostMapping("/admin/cabania/editarCabaña/editar")
    public String editarCabanaForm(@ModelAttribute Cabania cabania, String nombre, Integer capacidad) {
        try {
          
            cabaniaServicio.editarCabana(cabania.getId(), nombre, capacidad);
            return "redirect:/admin/verCabañas";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() != null) {
                System.err.println("Cause: " + e.getCause().getMessage());
            }
            return "error.html";
        }
    }
        @GetMapping("/admin/usuarios")
    public String usuarios(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();

        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        List<Reserva> reservas = reservaServicio.listarReservas();
        model.addAttribute("reserva", reservas);

        model.addAttribute("nombreUsuario", username);
        return "listarUsuarios.html";
    }

    @GetMapping("/admin/usuarios/editarUsuario/{id}")
    public String editarUsuario(@AuthenticationPrincipal UserDetails userDetails, Model model, @PathVariable Integer id) {
        Usuario us = usuarioServicio.listarUsuarioPorId(id);
        model.addAttribute("usuario", us);
        String username = userDetails.getUsername();
        model.addAttribute("nombreUsuario", username);
        return "editarUsuario.html";

    }

    @PostMapping("/admin/usuarios/editarUsuario/editar")
    public String editarUsuarioForm(@ModelAttribute Usuario usuario, String username, String nombre, boolean estado, String rol, String password,
            String contacto, String contactoEmergencia, String nombreContactoEmergencia, String parentesco, String email, LocalDate fechaNacimiento, Model model) throws MiExcepcion, Exception {

        try {
            
            usuarioServicio.editar(usuario.getId(), nombre, username, password, contacto, contactoEmergencia, nombreContactoEmergencia, parentesco, email, fechaNacimiento);
            return "redirect:/admin/usuarios";
        } catch (Exception e) {
            // Log the complete stack trace
            e.printStackTrace();

            // You can also log the cause of the exception if available
            if (e.getCause() != null) {
                System.err.println("Cause: " + e.getCause().getMessage());
            }

            // You can return an error page or redirect the user to an error page
            return "error.html";
        }

    }

}
