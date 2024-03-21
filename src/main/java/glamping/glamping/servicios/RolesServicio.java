/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;

import glamping.glamping.entidades.Roles;
import glamping.glamping.entidades.Usuario;
import glamping.glamping.repositorios.RolesRepositorio;
import glamping.glamping.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class RolesServicio {
    @Autowired
    private RolesRepositorio rolesRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Transactional
    public void cambiarRol(Integer id, String rol){
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        Roles role = new Roles();
        role.setName(rol);
        List<Roles> rolLista = new ArrayList();
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            rolLista.add(role);
            usuario.setRoles(rolLista);
        }
    }
    public List<Object[]> listarRol(){
       
       return rolesRepositorio.idUsuarioConNombreRol();
    }
    
}
