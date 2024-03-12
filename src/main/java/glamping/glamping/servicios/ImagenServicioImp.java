/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package glamping.glamping.servicios;

import com.glamping.Glamping.exceptions.MiException;
import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Imagen;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */

public interface ImagenServicioImp {
    public Imagen saveAttachment(MultipartFile file, Cabania cabania) throws MiException, IOException;
    public void saveFiles(MultipartFile[] files, Cabania cabania) throws MiException;
    public List<Imagen> getAllFiles();
    
}
