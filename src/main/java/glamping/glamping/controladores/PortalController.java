/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping("/")
    public String inicio(){
        return "inicio.html";
    }
    @GetMapping("/usuario")
    public String usuario(@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        ModelAndView model = new ModelAndView();
        model.addObject("nombreUsuario", username);
        return "usuario.html";
    }
   
    @GetMapping("/admin")
    public String admin(){
        return "admin.html";
    }
    @GetMapping("/usuario/reservaForm")
    public String reservaForm(){
        return "reservaForm.html";
    }
    
}
