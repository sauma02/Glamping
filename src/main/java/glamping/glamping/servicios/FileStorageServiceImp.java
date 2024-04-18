/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;

import glamping.glamping.entidades.Imagen;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service
public class FileStorageServiceImp implements FileStorageService {

    private final Path root = Paths.get("src/main/resources/img/");
    //Se inicia el servicio para la creacion del directorio
    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar la carpeta para la carga");
        }
    }

    @Override
   public void save(MultipartFile file) {
    try {
        
        Path filePath = this.root.resolve(file.getOriginalFilename());
        if (Files.exists(filePath)) {
            throw new FileAlreadyExistsException("Ya existe este archivo: " + filePath);
        }
        Files.copy(file.getInputStream(), filePath);
    } catch (IOException e) {
        throw new RuntimeException("Error al guardar el archivo: " + e.getMessage(), e);
    }
}

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se ha podido leer el archivo");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: "+e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
        
    }
    

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("No se puedo cargar el archivo");
        }
    }

    @Override
    public MultipartFile listOneFile(MultipartFile file) {
          return file;
    }

    @Override
    public void deleteFileByName(Imagen img) {
        try {
            FileSystemUtils.deleteRecursively(root.resolveSibling(img.getFileName()));
        } catch (IOException ex) {
           ex.printStackTrace();
           if(ex.getCause() != null){
               System.err.println("Error: "+ex.getCause().getMessage());
           }
        }
    }
    
}
