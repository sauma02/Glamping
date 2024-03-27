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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface RolesRepositorio extends JpaRepository<Roles, Integer> {
    public Roles findFirstById(Integer id);
    public Roles findByName(String name);
    
    public Roles findFirstByName(String name);
    @Query(value = "SELECT r.rol_id as idRol, r.nombre as nombreRol FROM roles r\n" +
"INNER JOIN usuario u\n" +
"WHERE u.roles_rol_id = 1 and r.rol_id = 1", nativeQuery = true )
    public Roles findByRoleId(@Param("id") Integer id);

    
   
    
}
