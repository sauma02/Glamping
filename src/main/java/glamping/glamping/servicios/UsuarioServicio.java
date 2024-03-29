/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;





import glamping.glamping.entidades.Roles;
import glamping.glamping.entidades.Usuario;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.repositorios.RolesRepositorio;
import glamping.glamping.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UsuarioServicio implements UserDetailsService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private RolesRepositorio rolesRepositorio;
    @Transactional
    public Usuario getCurrentUser(){
    // Obtener la autenticación actual desde el contexto de seguridad de Spring
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Verificar si la autenticación es nula o el usuario no está autenticado
        if (authentication == null || !authentication.isAuthenticated()) {
            return null; // Retornar nulo si no hay usuario autenticado
        }
        // Obtener el nombre de usuario del usuario autenticado
        String username = authentication.getName();
            // Aquí puedes implementar la lógica para cargar el usuario desde tu sistema de persistencia
        // Por ejemplo, desde una base de datos
        
        // Supongamos que tienes un método en tu repositorio de usuario para buscar por nombre de usuario
        Usuario user = usuarioRepositorio.findByUsername(username);
        
        return user;
    }
    public Usuario getCurrentUserByEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }
        String email = authentication.getName();
        Usuario user = usuarioRepositorio.findByEmail(email);
        return user;
    }
    public void crearUsuarioAdmin(String nombre, String username, String password,String contacto, String contactoEmergencia,
            String nombreContactoEmergencia, String parentesco, String email, LocalDate fechaNacimiento){
         Roles rol = rolesRepositorio.findFirstById(2);
        if(rol == null){
            rol = new Roles(null, "admin");
            rolesRepositorio.save(rol);
        }
            
        
      
        Usuario admin = new Usuario();
        admin.setNombre(nombre);
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setRoles(rol);
        usuarioRepositorio.save(admin);
    }
    public void crearUsuario(String nombre, String username, String password,String contacto, String contactoEmergencia,
            String nombreContactoEmergencia, String parentesco, String email, LocalDate fechaNacimiento) throws MiExcepcion{
        validar(nombre, username, password, email, fechaNacimiento);
        Roles rol = rolesRepositorio.findFirstById(1);
        if(rol == null){
            rol = new Roles(null, "usuario");
            rolesRepositorio.save(rol);
        }
            
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setContacto(contacto);
        usuario.setContactoEmergencia(contactoEmergencia);
        usuario.setNombreContactoEmergencia(nombreContactoEmergencia);
        usuario.setParentesco(parentesco);
        usuario.setEmail(email);
        usuario.setFechaNacimiento(fechaNacimiento);
//        usuario.setRoles(rol);
        usuarioRepositorio.save(usuario); 
        
     
    }
    public void editar(Integer id, String nombre, String username, String password,String contacto, String contactoEmergencia,
            String nombreContactoEmergencia, String parentesco, String email, LocalDate fechaNacimiento) throws MiExcepcion{
        validar(nombre, username, password, email, fechaNacimiento);
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
        usuario.setNombre(nombre);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setContacto(contacto);
        usuario.setContactoEmergencia(contactoEmergencia);
        usuario.setNombreContactoEmergencia(nombreContactoEmergencia);
        usuario.setParentesco(parentesco);
        usuario.setEmail(email);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuarioRepositorio.save(usuario);
        }
    }
    
    public Usuario listarUsuarioPorId(Integer id){
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            return usuario;
            
        }else{
            return null;
        }
    }
    public void eliminarUsuario(Integer id){
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuarioRepositorio.delete(usuario);
        }
    }
    public List<Usuario> listarUsuarios(){
       return usuarioRepositorio.findAll();
    }
    public Usuario listarUsuarioPorEmail(String email){
        Usuario usuario = usuarioRepositorio.findByEmail(email);
        return usuario;
    }
    
    public void validar(String nombre, String username, String password, 
            String email, LocalDate fechaNacimiento) throws MiExcepcion{
        if(nombre.isEmpty() || nombre == null){
            throw new MiExcepcion("El nombre no puede ser nulo");
        }
         if(username.isEmpty() || username == null){
            throw new MiExcepcion("El username no puede ser nulo");
        }
          if(password.isEmpty() || password == null){
            throw new MiExcepcion("El password no puede ser nulo");
        }
           if(email.isEmpty() || email == null){
            throw new MiExcepcion("El email no puede ser nulo");
        }
            if(fechaNacimiento == null){
            throw new MiExcepcion("El fechaNacimiento no puede ser nulo");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException("El usuario ingresado no existe");
        }else{
            return usuario;
        }
        
        
    }
    
}
