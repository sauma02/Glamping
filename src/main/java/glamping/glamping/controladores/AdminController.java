/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.entidades.Cabania;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.servicios.CabaniaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author USUARIO
 */
@Controller
public class AdminController {
    @Autowired
    private CabaniaServicio cabaniaServicio;
    
    
     @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String username = userDetails.getUsername();
        
        
        model.addAttribute("nombreUsuario", username);
        
        return "admin.html";
    }
    @GetMapping("/admin/Usuarios")
    public String usuarios(@AuthenticationPrincipal UserDetails userDetails, Model model){
           String username = userDetails.getUsername();
        
        
        model.addAttribute("nombreUsuario", username);
        return "adminUsuarios.html";
    }
    
    @GetMapping("/admin/registrarCabañas")
    public String registrarCabania(@AuthenticationPrincipal UserDetails userDetails, Model model){
           String username = userDetails.getUsername();
        
        
        model.addAttribute("nombreUsuario", username);
        return "registrarCabania.html";
    }
    @PostMapping("/admin/registrarCabañas/registrarCabaña")
    public String registerForm(@ModelAttribute("cabania") Cabania cabania, @RequestParam("nombre") String nombre, @RequestParam("capacidad") Integer capacidad,
            @RequestParam("imagen") String imagen, @RequestParam("estado") String estado, Model map) throws MiExcepcion{
        try {
            boolean status = Boolean.parseBoolean(estado);
            Cabania cab = cabaniaServicio.listarCabaniaPorNombre(nombre);
            if(cab!=null){
                map.addAttribute("errorCabaniaNombre", "Esta cabaña ya se encuentra registrada");
                return "registrarCabania.html";
            }else{
                
            cabaniaServicio.crearCabania(nombre, imagen, capacidad, status);
            map.addAttribute("exitoCabaniaRegsitro", "Exito al registrar la cabaña");
                    return "registrarCabania.html";
            }
            
            
        } catch (MiExcepcion e) {
            throw new MiExcepcion("Error al registrar");
        } 
    }
    @GetMapping("/admin/listarCabañas")
    public String listarCabania(){
        return null;
    }
}
