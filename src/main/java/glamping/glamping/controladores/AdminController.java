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
    public String admin(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String username = userDetails.getUsername();
        
        
        model.addAttribute("nombreUsuario", username);
        
        return "admin.html";
    }
    @GetMapping("/admin/usuarios")
    public String usuarios(@AuthenticationPrincipal UserDetails userDetails, Model model){
           String username = userDetails.getUsername();
        
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        List<Reserva> reservas = reservaServicio.listarReservas();
        model.addAttribute("reserva", reservas);
  
        model.addAttribute("nombreUsuario", username);
        return "listarUsuarios.html";
    }
    
    @GetMapping("/admin/registrarCabañas")
    public String registrarCabania(@AuthenticationPrincipal UserDetails userDetails, Model model){
           String username = userDetails.getUsername();
        
        
        model.addAttribute("nombreUsuario", username);
        return "registrarCabania.html";
    }
    @GetMapping("/admin/verCabañas")
    public String listarCabanias(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String username = userDetails.getUsername();
        List<Cabania> listaCabanias = cabaniaServicio.listarCabanias();
        for (Cabania cab : listaCabanias) {
            model.addAttribute("cabania", cab);
        }
        model.addAttribute("listaCabanias", listaCabanias);
        model.addAttribute("nombreUsuario", username);
        return "listarCabania.html";
    }
    @PostMapping("admin/usuarios/editarUsuario/{id}")
    public String editarUsuario(@PathVariable("id") Integer id, @ModelAttribute Usuario usuario, String username, String nombre, boolean estado, String rol, String password, 
            String contacto, String contactoEmergencia, String nombreContactoEmergencia, String parentesco, String email, LocalDate fechaNacimiento, Model model ) throws MiExcepcion, Exception{
        try {
              usuarioServicio.editar(id, nombre, username, password, contacto, contactoEmergencia, nombreContactoEmergencia, parentesco, email, fechaNacimiento);
              return "listarUsuarios.html";
        } catch (Exception e) {
            throw new Exception("Error inesperado" + e.getCause());
        }
     
        
        
}
    
   

   

  


        
    @GetMapping("/admin/listarCabañas")
    public String listarCabania(){
        return null;
    }
}
