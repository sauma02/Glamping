/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;





import glamping.glamping.entidades.Roles;
import glamping.glamping.entidades.Usuario;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Transactional
    public void crearUsuarioAdmin(String nombre, String username, String password,String contacto, String contactoEmergencia,
            String nombreContactoEmergencia, String parentesco, String email, Date fechaNacimiento, Roles rol){
        List<Roles> rolLista = new ArrayList();
        rol.setNombre("admin");
        
        rolLista.add(rol);
        Usuario admin = new Usuario();
        admin.setNombre(nombre);
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setRoles(rolLista);
        usuarioRepositorio.save(admin);
    }
    public void crearUsuario(String nombre, String username, String password,String contacto, String contactoEmergencia,
            String nombreContactoEmergencia, String parentesco, String email, Date fechaNacimiento, Roles rol) throws MiExcepcion{
        validar(nombre, username, password, email, fechaNacimiento);
        List<Roles> roles = new ArrayList();
        
        rol.setNombre("usuario");
        roles.add(rol);
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
        usuario.setRoles(roles);
        usuarioRepositorio.save(usuario);
    }
    public void editar(Integer id, String nombre, String username, String password,String contacto, String contactoEmergencia,
            String nombreContactoEmergencia, String parentesco, String email, Date fechaNacimiento) throws MiExcepcion{
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
    
    public void validar(String nombre, String username, String password, 
            String email, Date fechaNacimiento) throws MiExcepcion{
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
    
}
