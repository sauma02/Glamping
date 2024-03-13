/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;


import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Imagen;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.repositorios.ImagenRepositorio;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service
public class ImagenServicio implements ImagenServicioImp{
    @Autowired
    private ImagenRepositorio imagenRepositorio;
    //Se crea una entidad imagen, con su respectivo respositorio y servicio, pero
    //se crea con una interfaz para manejas el guardado de los files
    @Override
    public Imagen saveAttachment(MultipartFile file, Cabania cabania) throws MiExcepcion, IOException {
     //Se trae primero el nombre del file utilizando String utils clean path
     String fileName = StringUtils.cleanPath(file.getOriginalFilename());
     //Se verifica que el archivo contenga una secuencia valida
        try {
            if(fileName.contains("..")){
                throw new MiExcepcion("El archivo contiene una secuencia invalida");
            }
            if(file.getBytes().length > (1600 * 1600 )){
                throw new MiExcepcion("El archivo sobre pasa el limite maximo");
                
            }
            //Se crea el attachment donde se guarda y se crea el objeto imagen
            Imagen attachment = new Imagen(null, fileName, file.getContentType(), cabania, file.getBytes());
            return imagenRepositorio.save(attachment);
        } catch (MaxUploadSizeExceededException e) {
            throw new MaxUploadSizeExceededException(file.getSize());
        } catch (MiExcepcion e){
            throw new MiExcepcion("No se pudo cargar el archivo "+fileName);
        } 
        
     
    }

    @Override
    public void saveFiles(MultipartFile[] files, Cabania cabania) throws MiExcepcion {
        //Se utiliza una expresion lambda dentro del for each
       Arrays.asList(files).forEach((file) -> {
           try{
               saveAttachment(file, cabania);
           }catch (Exception e){
               throw new RuntimeException(e);
               
           }
       });

   }
    

    @Override
    public List<Imagen> getAllFiles() {
        return imagenRepositorio.findAll();
    }
    
}
