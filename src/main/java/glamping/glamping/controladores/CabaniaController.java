/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Imagen;
import glamping.glamping.entidades.Informacion;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.servicios.CabaniaServicio;
import glamping.glamping.servicios.FileStorageService;
import glamping.glamping.servicios.ImagenServicio;
import glamping.glamping.servicios.InformacionServicio;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

public class CabaniaController {
        @Autowired
    private CabaniaServicio cabaniaServicio;
    @Autowired 
    private FileStorageService storageService;
    @Autowired
    private ImagenServicio imagenServicio;
    @Autowired 
    private InformacionServicio informacionServicio;

       @GetMapping("/cabania")
    public String cabania(ModelMap map){
        List<Cabania> cabaniaLista = cabaniaServicio.listarCabanias();
        map.addAttribute("cabaniaLista", cabaniaLista);
        List<Informacion> listaInfo = informacionServicio.listarInformacion();
        System.out.println("Lista: "+listaInfo.toString());
        map.addAttribute("listaInfo", listaInfo);
        return "cabania.html";
    }
   
}
