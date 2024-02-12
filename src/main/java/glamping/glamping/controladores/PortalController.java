/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;


import glamping.glamping.entidades.Usuario;
import glamping.glamping.repositorios.UsuarioRepositorio;
import glamping.glamping.servicios.UsuarioServicio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String admin(){
        return "admin.html";
    }
    @GetMapping("/usuario/reservaForm")
    public String reservaForm(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String username = userDetails.getUsername();
        
        model.addAttribute("nombreUsuario", username);
        return "reservaForm.html";
    }
    @GetMapping("/register")
    public String registroForm(Model model){
        Usuario user = new Usuario();
        model.addAttribute("user", user);
        return "registroForm.html";
       
    }
    @PostMapping("/register/regis")
    public String submitForm(@ModelAttribute("user") Usuario user, @RequestParam String nombre,
            @RequestParam String username,@RequestParam String email, 
        @RequestParam String password, @RequestParam String contacto, 
        @RequestParam String contactoEmergencia,@RequestParam String nombreContactoEmergencia,
        @RequestParam String parentesco,@RequestParam Date fechaNacimiento,ModelMap map){
        try {
            //Este metodo recibe los parametros del formulario registrar usuario
            //con la ayuda del usuario servicio podemos registrar al usuario
            if(usuarioServicio.getCurrentUserByEmail() == null || usuarioServicio.getCurrentUser() == null){
                usuarioServicio.crearUsuario(nombre, username, password, contacto, contactoEmergencia, nombreContactoEmergencia, parentesco, email, fechaNacimiento);
                map.put("Exito", "Usuario registrado con exito");
                return "inicio.html";
            }else if(usuarioServicio.getCurrentUser() == user){
               map.put("Error_user", "El nombre de usuario ya esta en uso");
               return "registroForm.html";
            }else if(usuarioServicio.getCurrentUserByEmail() == user){
               map.put("Error_email", "El nombre de usuario ya esta en uso"); 
               return "registroForm.html";
            }
            
            
        } catch (Exception e) {
                
        }
        return "registerSuccess";
    }
    
}
