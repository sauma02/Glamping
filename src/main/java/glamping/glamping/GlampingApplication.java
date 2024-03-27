package glamping.glamping;

import glamping.glamping.entidades.Roles;
import glamping.glamping.entidades.Usuario;
import glamping.glamping.repositorios.RolesRepositorio;
import glamping.glamping.repositorios.UsuarioRepositorio;
import glamping.glamping.servicios.FileStorageService;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GlampingApplication {

    @Resource
    private FileStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(GlampingApplication.class, args);
    }

    /**
     *
     * @param arg
     */
    public void run(String... arg) {
        storageService.init();
    }

    ;
      @Bean
    public CommandLineRunner commandLineRunner(UsuarioRepositorio usuarioRepositorio, RolesRepositorio rolesRepositorio, PasswordEncoder passwordEncoder) {
        return args -> {
            Usuario usuario = usuarioRepositorio.findByUsername("user");
            Usuario adminRol = usuarioRepositorio.findByUsername("admin");
            Roles user = rolesRepositorio.findByName("usuario");
            Roles admin = rolesRepositorio.findByName("admin");
            if (usuario != null && user != null) {
                System.out.println("Usuario ya existe");
            } else {
                Roles us = new Roles();
                us.setName("usuario");
                rolesRepositorio.save(us);
                Usuario user1 = new Usuario(Integer.SIZE, "jose", "user", passwordEncoder.encode("password"), null, null,
                        null, null, null,
                        "josedavids123@live.com", us, null);
                usuarioRepositorio.save(user1);

            }
            if (adminRol != null && admin != null) {
                System.out.println("Usuario admin ya existe");
            } else {
                Roles ad = new Roles();
                ad.setName("admin");
                rolesRepositorio.save(ad);
                Usuario usuarioAdmin = new Usuario(Integer.SIZE, "jose", "admin", passwordEncoder.encode("password"), null, null,
                        null, null, null,
                        "josedavids123@gmail.com", ad, null);
                usuarioRepositorio.save(usuarioAdmin);

            }
        };

    }

}
