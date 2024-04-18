/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;

import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Imagen;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.repositorios.CabaniaRepositorio;
import glamping.glamping.repositorios.ImagenRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
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
public class CabaniaServicio {
    @Autowired
    private CabaniaRepositorio cabaniaRepositorio;
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private ImagenServicio imagenServicio;
    @Autowired
    private ImagenRepositorio imagenRepositorio;
    @Transactional
    public void crearCabania(Cabania cabania){
        cabaniaRepositorio.save(cabania);
    }
    public String registrarImagenCabania(String imagen){
        return imagen;
        
        
    }
    public void editarCabana(Integer id, String nombre, Integer capacidad){
        try {
            Optional<Cabania> respuesta = cabaniaRepositorio.findById(id);
        if(respuesta.isPresent()){
           
            Cabania cabania = respuesta.get();
         
            
            cabania.setNombre(nombre);
            cabania.setCapacidad(capacidad);
          
       
            cabaniaRepositorio.save(cabania);
            
            
        } 
        }catch (Exception e) {
                e.printStackTrace();
                
                if(e.getCause() != null){
                    System.err.print("Error con causa: "+e.getMessage());
                }
                
                }
        
        }    
    public void editarImagenes(Integer id, Cabania cabania, List<Imagen> img, MultipartFile imagen){
        try {
            for (Imagen imagen1 : img) {
                if(imagen1.getId() == id){
                    storageService.init();
                    storageService.save(imagen);
                    imagenServicio.eliminarImagen(imagen1);
                    Imagen img2 = new Imagen();
                    img2.setFileName(storageService.listOneFile(imagen).getOriginalFilename());
                    imagenServicio.guardarImagen(cabania, img2, imagen);
                    img.add(img2);
                    cabania.setImagen(img);
                    cabaniaRepositorio.save(cabania);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            
            if(e.getCause() != null){
                System.err.println("Error: "+e.getCause().getMessage());
            }
            
        }
        
    }
    public void añadirImagenes(Cabania cabania, Imagen img, MultipartFile imagen){
            try {
            List<Imagen> imagenes = cabania.getImagen();
            storageService.init();
            storageService.save(imagen);
            img.setFileName(storageService.listOneFile(imagen).getOriginalFilename());
            imagenes.add(img);
            imagenServicio.guardarImagen(cabania, img, imagen);
            cabania.setImagen(imagenes);
            cabaniaRepositorio.save(cabania);
        } catch (Exception e) {
            e.printStackTrace();
            if(e.getCause() != null){
                System.err.println("Error: "+e.getCause().getMessage());
            }
        }
            
            
        
    }        
    public void eliminarImagen(List<Imagen> imagenes, Cabania cabania, Integer id){
        
        for (Imagen img : imagenes) {
            if(img.getId() == id){
                imagenServicio.eliminarImagen(img);
            }
            cabania.setImagen(imagenes);
            
        }
        cabaniaRepositorio.save(cabania);
        
        
    }        
        
    

    public Cabania listarCabaniaPorId(Integer id){
        Optional<Cabania> respuesta = cabaniaRepositorio.findById(id);
        if(respuesta.isPresent()){
            Cabania cabania = respuesta.get();
            return cabania;
        }else{
            return null;
        }
    }
    public void eliminarCabaniaPorId(Integer id){
        Optional<Cabania> respuesta = cabaniaRepositorio.findById(id);
        if(respuesta.isPresent()){
            Cabania cabania = respuesta.get();
            cabaniaRepositorio.delete(cabania);
        }
   }
    public Cabania listarCabaniaPorNombre(String nombre){
        Cabania respuesta = cabaniaRepositorio.findFirstByNombre(nombre);
        if(respuesta!=null){
          return respuesta;   
        }else{
            return null;
        }
       
        
    }
    public List<Cabania> listarCabanias(){
        return cabaniaRepositorio.findAll();
    }
    public void validar(String nombre, Integer capacidad, Imagen imagen, Boolean estado) throws MiExcepcion{
        if(nombre.isEmpty() || nombre==null){
            throw new MiExcepcion("El nombre no puede ser nulo");
        }
        if(capacidad==null){
            throw new MiExcepcion("El capacidad no puede ser nulo");
        }
        if(imagen==null){
            throw new MiExcepcion("El imagen no puede ser nulo");
        }
        if(estado==null){
            throw new MiExcepcion("El estado no puede ser nulo");
        }
    }
    
}
