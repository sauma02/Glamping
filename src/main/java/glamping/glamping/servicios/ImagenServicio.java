/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;

import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Imagen;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.repositorios.ImagenRepositorio;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service
public class ImagenServicio {
    @Autowired
    private ImagenRepositorio imagenRepositorio;
    @Transactional
    public void guardarImagen(Cabania cabania, Imagen imagen, MultipartFile file) throws MiExcepcion{
        try {
            Path ruta = Paths.get("src/main/resources/img" + file.getOriginalFilename());
            imagen.setRuta(ruta.toString());
            imagen.setTamano(file.getSize());
            imagen.setFileName(file.getOriginalFilename());
            imagen.setFileType(file.getContentType());
            imagen.setCabania(cabania);
            imagenRepositorio.save(imagen);
            
      
        } catch (Exception ex){
            throw new MiExcepcion("Error al guardar imagen" + ex.getCause());
            
        }
              
    }
    
    
}
