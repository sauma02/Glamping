/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Imagen;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.servicios.CabaniaServicio;
import glamping.glamping.servicios.FileStorageService;
import glamping.glamping.servicios.ImagenServicio;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/cabania")
public class CabaniaController {
        @Autowired
    private CabaniaServicio cabaniaServicio;
    @Autowired 
    private FileStorageService storageService;
    @Autowired
    private ImagenServicio imagenServicio;

       @GetMapping("/cabania")
    public String cabania(){
        return "cabania.html";
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
        return "listarCabañas.html";
    }

}
