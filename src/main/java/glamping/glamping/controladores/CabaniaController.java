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

    @GetMapping("/Cabania")
    public String cabania(){
        return "cabania.html";
    }
     @PostMapping("/admin/registrarCabañas/registrarCabaña")
    public String registerForm(@RequestParam("nombre") String nombre, 
            @RequestParam("capacidad") Integer capacidad,@RequestParam("imagen") MultipartFile imagen, @RequestParam("estado") boolean estado, Model map) throws MiExcepcion, IOException, Exception{
            String mensaje = "";
        try {
            Cabania cab = cabaniaServicio.listarCabaniaPorNombre(nombre);
            if(cab != null){
                map.addAttribute("errorCabaniaExistente","La cabaña con el nombre "+nombre+" ya existe");
                map.addAttribute("estado", estado);
                map.addAttribute("capacidad", capacidad);
                mensaje="la cabaña indicada ya existe";
               return "registrarCabania.html";
            }
            if(imagen == null || imagen.isEmpty()){
                map.addAttribute("imagenVacia", "Imagen nula");
                throw new Exception("Error, la imagen es nula, no puede ser nula");
                
            }else{
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
               mensaje="admin.html";
               return mensaje;
            }

        
        
        } catch (SQLException e) {
            throw new Exception("Error en la creacion de la cabaña", e.getCause());
            
            
        }        
    }
    
}
