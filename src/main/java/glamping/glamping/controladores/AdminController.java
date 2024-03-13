/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.config.FileUploadUtil;
import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Imagen;
import glamping.glamping.entidades.ImagenResponse;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.servicios.CabaniaServicio;
import glamping.glamping.servicios.FileStorageService;
import glamping.glamping.servicios.ImagenServicio;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
import org.springframework.web.bind.annotation.PostMapping;
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
    private FileStorageService fileStorageService;
    @Autowired
    private ImagenServicio imagenServicio;
    
    
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
    public String registerForm(@RequestParam("nombre") String nombre, 
            @RequestParam("capacidad") Integer capacidad,@RequestParam("imagen") MultipartFile imagen, @RequestParam("estado") boolean estado, Model map) throws MiExcepcion, IOException, Exception{
        try {
            Cabania cab = cabaniaServicio.listarCabaniaPorNombre(nombre);
            if(cab != null){
                map.addAttribute("errorCabaniaExistente","La cabaña con el nombre "+nombre+" ya existe");
                map.addAttribute("estado", estado);
                map.addAttribute("capacidad", capacidad);
                return "registrarCabania.html";
            }

        // Manejar la carga de la imagen y guardarla
        Cabania cabania = new Cabania();
        Imagen nuevaImagen = new Imagen();
        nuevaImagen.setFileName(imagen.getOriginalFilename());
        nuevaImagen.setFileType(imagen.getContentType());
        nuevaImagen.setData(imagen.getBytes());
        nuevaImagen.setCabania(cabania);

        // Establecer los demás atributos de la cabaña
        cabania.setNombre(nombre);
        cabania.setCapacidad(capacidad);
        cabania.setEstado(estado);
        
        // Asignar la imagen a la lista de imágenes de la cabaña
        List<Imagen> listaImagenes = new ArrayList<>();
        listaImagenes.add(nuevaImagen);
        cabania.setImagen(listaImagenes);

        // Guardar la cabaña en la base de datos
        cabaniaServicio.crearCabania(cabania);

        // Informar sobre el éxito del registro
        map.addAttribute("exitoCabania", "Éxito al crear cabaña");
        return "registrarCabania.html";
            
        } catch (Exception e) {
            throw new MiExcepcion("Error al crear cabaña"+e.toString());
        
        }
        
    }
   
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public ResponseEntity<ImagenResponse> handleFileUpload(@RequestParam("imagen") MultipartFile imagen) throws Exception{
        String fileName = imagen.getOriginalFilename();
        try {
            imagen.transferTo(new File("\\src\\main\\resources\\img\\"+fileName));
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(fileName)
                    .toUriString();
            ImagenResponse respuesta = new ImagenResponse(fileName, downloadUrl, imagen.getContentType(), imagen.getSize());
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }
  


        
    @GetMapping("/admin/listarCabañas")
    public String listarCabania(){
        return null;
    }
}
