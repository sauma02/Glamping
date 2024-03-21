/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.entidades;

/**
 *
 * @author Admin
 */
public class UsuarioRolesDTO {
    public Integer usuarioId;
    public Integer rolesId;

    public UsuarioRolesDTO(Integer usuarioId, Integer rolesId) {
        this.usuarioId = usuarioId;
        this.rolesId = rolesId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

    @Override
    public String toString() {
        return "UsuarioRolesDTO{" + "usuarioId=" + usuarioId + ", rolesId=" + rolesId + '}';
    }
    
    
}
