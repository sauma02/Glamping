package com.glamping.Glamping;

import com.glamping.Glamping.entidades.Role;
import com.glamping.Glamping.entidades.Usuario;
import com.glamping.Glamping.enumeraciones.Rol;
import com.glamping.Glamping.repositorios.RoleRepositorio;
import com.glamping.Glamping.repositorios.UsuarioRepositorio;
import com.glamping.Glamping.servicios.UsuarioServicio;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GlampingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlampingApplication.class, args);
	}
        @Bean
                //Con esta funcion se compila un codigo al iniciar la aplicacion, creando usuario y otros parametros necesarios
        CommandLineRunner run(RoleRepositorio rolRep, UsuarioRepositorio usuRep, PasswordEncoder encoder){
            //Funcion Lambda
            return args ->{
                //Se hace la verificacion para validar que existan o no los roles de ADMIN
                if(rolRep.findByAuthority("ADMIN").isPresent()) return;
                Role adminRole = rolRep.save(new Role("ADMIN"));
                rolRep.save(new Role("USUARIO"));
                //Se crea un nuevo set para hacer la asignacion
                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);
                Date fecha = new Date();
                Usuario admin = new Usuario(1, "ADMIN", "Ad", fecha, "2314234234"
                        , "MED", "asdasdasdasd@live.com", encoder.encode("password"), Set.of(adminRole));
                usuRep.save(admin);
                
                
            };
        }
}
