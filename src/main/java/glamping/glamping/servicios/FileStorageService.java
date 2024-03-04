/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package glamping.glamping.servicios;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author USUARIO
 */
public interface FileStorageService {
    public void init();
    
    public void save(MultipartFile file);
    public Resource load(String fileName);
    public void deleteAll();
    public Stream<Path> loadAll();
}
