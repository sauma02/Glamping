/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;

import glamping.glamping.entidades.Imagen;
import glamping.glamping.entidades.Informacion;
import glamping.glamping.repositorios.InformacionRepositorio;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service
public class InformacionServicio {
    @Autowired
    private InformacionRepositorio informacionRepositorio;
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private ImagenServicio imagenServicio;
    @Transactional
    public Informacion crearNuevaInfo(String titulo, String iconoServicio, String texto, String seccion, MultipartFile imagen){
        try {
            if(imagen.isEmpty()){
                Informacion info = new Informacion(null, titulo, texto, iconoServicio,seccion, null);
                 informacionRepositorio.save(info);
                 return info;
           
             
            }else{
            storageService.save(imagen);
            Imagen img = new Imagen();
            img.setFileName(storageService.listOneFile(imagen).getOriginalFilename());
            
            Informacion info = new Informacion(null, titulo,texto,iconoServicio, seccion, img);
            imagenServicio.guardarImagenInfo(info, img, imagen);
            informacionRepositorio.save(info);
            return info;
            }
           
       
        } catch (Exception e) {
            e.printStackTrace();
            
            if(e.getCause() != null){
                System.err.println("Error al crear info: "+e.getCause().getMessage());
            }
            return null;
        }
        
        
    }
    public void editarInfo(Informacion info){
       informacionRepositorio.save(info);
        
    }
    public void eliminarInfo(Integer id){
        Optional<Informacion> optionalInfo = informacionRepositorio.findById(id);
        if(optionalInfo.isPresent()){
            Informacion info = optionalInfo.get();
             Imagen img = imagenServicio.imagenPorId(id);
            
            informacionRepositorio.delete(info);
        }
    }
    public Informacion buscarPorId(Integer id){
        Optional<Informacion> optionalInfo = informacionRepositorio.findById(id);
        if(optionalInfo.isPresent()){
            Informacion info = optionalInfo.get();
           
            return info;
        }else{
            return null;
        }
    }
    public List<Informacion> listarInformacion(){
        return informacionRepositorio.findAll();
    }
}
