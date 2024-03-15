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
       public void run(String... arg){
           storageService.init();
       };
      @Bean
        public CommandLineRunner commandLineRunner(UsuarioRepositorio usuarioRepositorio, RolesRepositorio rolesRepositorio,  PasswordEncoder passwordEncoder){
            return args ->{
                Roles adminRol = rolesRepositorio.findFirstByName("admin");
                Roles usuario = rolesRepositorio.findFirstByName("usuario");
                if(usuario == null){
                    Roles user = new Roles();
                    user.setName("usuario");
                    rolesRepositorio.save(user);
                    List<Roles> listaRoles = new ArrayList();
                    listaRoles.add(user);
                    Usuario user1 = new Usuario(Integer.SIZE, "jose", "user", passwordEncoder.encode("password"), null, null, 
                        null, null, null, 
                        "josedavids123@live.com", listaRoles, null);
                    usuarioRepositorio.save(user1);
                }else{
                    System.out.println("Usuario ya existe");
                }
                if(adminRol == null){
                Roles admin = new Roles();
                admin.setName("admin");
                rolesRepositorio.save(admin);
                List<Roles> listaRoles = new ArrayList();
                listaRoles.add(admin);
                Usuario usuarioAdmin = new Usuario(Integer.SIZE, "jose", "admin", passwordEncoder.encode("password"), null, null, 
                        null, null, null, 
                        "josedavids123@live.com", listaRoles, null);
                usuarioRepositorio.save(usuarioAdmin);
                }else{
                    System.out.println("Usuario admin ya existe");
                }
            };
            
            
        }
        
}
