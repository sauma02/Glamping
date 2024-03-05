/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;

import glamping.glamping.entidades.Cabania;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.repositorios.CabaniaRepositorio;
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
public class CabaniaServicio {
    @Autowired
    private CabaniaRepositorio cabaniaRepositorio;
    @Transactional
    public void crearCabania(String nombre, String imagen, Integer capacidad, boolean estado) throws MiExcepcion{
        validar(nombre, capacidad, imagen, estado);
        Cabania cabania = new Cabania();
        cabania.setNombre(nombre);
        cabania.setImagen(imagen);
        cabania.setCapacidad(capacidad);
        cabania.setEstado(estado);
        cabaniaRepositorio.save(cabania);
        
    }
    public String registrarImagenCabania(String imagen){
        return imagen;
        
        
    }
    public void editarCabania(Integer id, String nombre, String imagen, Integer capacidad, boolean estado) throws MiExcepcion{
        validar(nombre, capacidad, imagen, estado);
        Optional<Cabania> respuesta = cabaniaRepositorio.findById(id);
        if(respuesta.isPresent()){
            Cabania cabania = respuesta.get();
            cabania.setNombre(nombre);
            cabania.setImagen(imagen);
            cabania.setCapacidad(capacidad);
            cabania.setEstado(estado);
            cabaniaRepositorio.save(cabania);
            
        }
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
    public void validar(String nombre, Integer capacidad, String imagen, Boolean estado) throws MiExcepcion{
        if(nombre.isEmpty() || nombre==null){
            throw new MiExcepcion("El nombre no puede ser nulo");
        }
        if(capacidad==null){
            throw new MiExcepcion("El capacidad no puede ser nulo");
        }
        if(imagen.isEmpty() || imagen==null){
            throw new MiExcepcion("El imagen no puede ser nulo");
        }
        if(estado==null){
            throw new MiExcepcion("El estado no puede ser nulo");
        }
    }
    
}
