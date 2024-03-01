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
import glamping.glamping.servicios.ReservaServicio;
import glamping.glamping.servicios.UsuarioServicio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
//RestController solo devolver lo que esta escrito explicitamente, para mostrar los archivos html toca utilizar
//la etiqueta controller
@Controller

public class PortalController {
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
    @GetMapping("/")
    public String inicio(){
        return "inicio.html";
    }
    @GetMapping("/usuario")
    public String usuario(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String username = userDetails.getUsername();
        
        model.addAttribute("nombreUsuario", username);
        return "usuario.html";
    }
   
    @GetMapping("/admin")
    public String admin(UserDetails userDetails, Model model){
        String username = userDetails.getUsername();
        
        
        model.addAttribute("nombreUsuario", username);
        
        return "admin.html";
    }
    @GetMapping("/usuario/reservaForm")
    public String reservaForm(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String username = userDetails.getUsername();
        List<Cabania> cabaniasDisponibles = cabaniaServicio.listarCabanias();
        model.addAttribute("nombreUsuario", username);
        model.addAttribute("cabaniasDisponibles", cabaniasDisponibles);
        return "reservaForm.html";
    }
    @GetMapping("/register")
    public String registroForm(Model model){
       
        return "registroForm.html";
       
    }
   
    @PostMapping("/register/regis")
    public String submitForm(@ModelAttribute("user") Usuario user, @RequestParam("nombre") String nombre,
            @RequestParam("username") String username,@RequestParam("email") String email, 
        @RequestParam("password") String password, @RequestParam("contacto") String contacto, 
        @RequestParam("contactoEmergencia") String contactoEmergencia,@RequestParam("nombreContactoEmergencia") String nombreContactoEmergencia,
        @RequestParam("parentesco") String parentesco,@RequestParam("fechaNacimiento") @DateTimeFormat(pattern = "dd/mm/yyyy") LocalDate fechaNacimiento,ModelMap map) throws Exception{
        try {
            //Este metodo recibe los parametros del formulario registrar usuario
            //con la ayuda del usuario servicio podemos registrar al usuario
            Usuario verificarEmail = usuarioRepositorio.findFirstByEmail(email);
            Usuario verificarUsuario = usuarioRepositorio.findByUsername(username);
            if(verificarEmail != null){
                
                map.addAttribute("errorEmail", "El email ya esta en uso");
                System.out.println("Existe");
            }
            if(verificarUsuario != null){
                map.addAttribute("errorUsuario", "El nombre de usuario ya esta en uso");
                System.out.println("Existe el usuario");
            }
            
            if(verificarUsuario == null && verificarEmail == null){
                usuarioServicio.crearUsuario(nombre, username, password, contacto, contactoEmergencia, nombreContactoEmergencia, parentesco, email, fechaNacimiento);
                map.addAttribute("Exito", "Usuario creado con exito");
                return "inicio.html";
            }
            return "registroForm.html";

            
            
        } catch (Exception e) {
            throw new Exception(e);
        }
        
    }
    
    
}
