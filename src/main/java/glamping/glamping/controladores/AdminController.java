/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.config.FileUploadUtil;
import glamping.glamping.entidades.Cabania;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.servicios.CabaniaServicio;
import glamping.glamping.servicios.FileStorageService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author USUARIO
 */
@Controller
public class AdminController {
    @Autowired
    private CabaniaServicio cabaniaServicio;
    @Autowired
    private FileStorageService fileStorageService;
    
    
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
            @RequestParam("imagen") MultipartFile imagen, @RequestParam("estado") String estado, Model map) throws MiExcepcion, IOException{
        String mensaje = "";
        try {
            
            Cabania cab = cabaniaServicio.listarCabaniaPorNombre(nombre);
            if(cab != null){
                mensaje = "la cabaña con este nombre ya se encuentra registrada";
                map.addAttribute("errorCabaniaExiste", mensaje);
            }else{
            if(!imagen.isEmpty()){
                Path directorioImagenes = Paths.get("src//main//resources//img");
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
                try {
                     byte[] byteImg = imagen.getBytes();
                     Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                     Files.write(rutaCompleta, byteImg);
                     String img = cabaniaServicio.registrarImagenCabania(imagen.getOriginalFilename());
                     cabaniaServicio.crearCabania(nombre, img, capacidad, true);
                     mensaje="Cabaña registrada con exito";
                     map.addAttribute("exitoRegistro", mensaje);
                     
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Subir archivos
               //https://www.youtube.com/watch?v=BjHEuNdpC-U&ab_channel=code503
            }
            }
            return "admin.html";
            } catch (Exception e) {
                e.printStackTrace();
                
        }
        return "redirect:/admin";    
            
         
            
            
            
        
    }
  
    
    @GetMapping("/admin/listarCabañas")
    public String listarCabania(){
        return null;
    }
}
