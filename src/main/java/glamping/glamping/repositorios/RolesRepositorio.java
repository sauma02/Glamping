/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package glamping.glamping.repositorios;

import glamping.glamping.entidades.Roles;
import glamping.glamping.entidades.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface RolesRepositorio extends JpaRepository<Roles, Integer> {

    public Roles findByName(String name);

    public Roles findFirstByName(String name);
    @Query(value = "SELECT r.nombre, u.usuario_id  from roles r, usuario u\n" +
            "inner join usuario_roles ur\n" +
            "where  ur.usuario_usuario_id = u.usuario_id and ur.roles_rol_id = r.rol_id", nativeQuery = true)
    public List<Object[]> idUsuarioConNombreRol();

    
   
    
}
