/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;

import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Imagen;
import glamping.glamping.entidades.Reserva;
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
    @Autowired
    private ReservaServicio reservaServicio;

    @Transactional
    public void crearCabania(Cabania cabania) {
        cabaniaRepositorio.save(cabania);
    }
    
    public void cambiarEstado(Integer id, boolean estado){
        
        try {
            Optional<Cabania> cabania = cabaniaRepositorio.findById(id);
            if(cabania.isPresent()){
                Cabania cab = cabania.get();
                cab.getCapacidad();
                cab.getNombre();
                cab.getReserva();
                cab.getImagen();
                cab.setEstado(estado);
                cabaniaRepositorio.save(cab);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
               
    }

    public String registrarImagenCabania(String imagen) {
        return imagen;

    }

    public void editarCabana(Integer id, String nombre, String descripcion, Integer capacidad) {
        try {
            Optional<Cabania> respuesta = cabaniaRepositorio.findById(id);
            if (respuesta.isPresent()) {

                Cabania cabania = respuesta.get();

                cabania.setNombre(nombre);
                cabania.setCapacidad(capacidad);
                cabania.setDescripcion(descripcion);

                cabaniaRepositorio.save(cabania);

            }
        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() != null) {
                System.err.print("Error con causa: " + e.getMessage());
            }

        }

    }

    public void editarImagenes(Cabania cabania){
        cabaniaRepositorio.save(cabania);
    }

    public void añadirImagenes(Cabania cabania, MultipartFile[] imagen) {
        try {
            

            for (MultipartFile file : imagen) {
                Imagen img = new Imagen();
                
                img.setFileName(storageService.listOneFile(file).getOriginalFilename());
                
                imagenServicio.guardarImagen(cabania, img, file);
            }
            
            cabaniaRepositorio.save(cabania);
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
        }

    }

    public void eliminarImagen(Integer id) {
        try {
            Imagen img = imagenServicio.imagenPorId(id);
            if (img != null) {
                Cabania cabania = img.getCabania();
                List<Imagen> imagenes = cabania.getImagen();
                if (imagenes != null) {
                    imagenes.removeIf(imagen -> imagen.getId().equals(id));
                    cabania.setImagen(imagenes);
                    cabaniaRepositorio.save(cabania);
                }
                imagenRepositorio.delete(img);
                storageService.delete(img);

            }

        } catch (Exception e) {

            e.printStackTrace();
            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
        }

    }

    public Cabania listarCabaniaPorId(Integer id) {
        Optional<Cabania> respuesta = cabaniaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cabania cabania = respuesta.get();
            return cabania;
        } else {
            return null;
        }
    }
@Transactional
    public void eliminarCabaniaPorId(Integer id) {
        Optional<Cabania> respuesta = cabaniaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cabania cabania = respuesta.get();
            imagenServicio.eliminarImagenPorCabania(cabania);
            reservaServicio.eliminarReservasPorCabania(cabania);
            
            cabaniaRepositorio.delete(cabania);
        }
    }

    public Cabania listarCabaniaPorNombre(String nombre) {
        Cabania respuesta = cabaniaRepositorio.findFirstByNombre(nombre);
        if (respuesta != null) {
            return respuesta;
        } else {
            return null;
        }

    }

    public List<Cabania> listarCabanias() {
        return cabaniaRepositorio.findAll();
    }

    public void validar(String nombre, Integer capacidad, Imagen imagen, Boolean estado) throws MiExcepcion {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiExcepcion("El nombre no puede ser nulo");
        }
        if (capacidad == null) {
            throw new MiExcepcion("El capacidad no puede ser nulo");
        }
        if (imagen == null) {
            throw new MiExcepcion("El imagen no puede ser nulo");
        }
        if (estado == null) {
            throw new MiExcepcion("El estado no puede ser nulo");
        }
    }

}
