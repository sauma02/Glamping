/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="Usuario_id")
    private Integer id;
    private String nombre;
    private String username;
    private String password;
    private String contactoEmergencia;
    private String contacto;
    private String NombreContactoEmergencia;
    private String parentesco;
    @DateTimeFormat(pattern = "dd/mm/yyyy") 
    private LocalDate fechaNacimiento;
    private String email;
    @PrimaryKeyJoinColumn(name="Rol_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Roles roles;
    
   @ManyToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    
    private List<Reserva> reserva;

    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String username, String password, String contactoEmergencia, String contacto, String NombreContactoEmergencia, String parentesco, LocalDate fechaNacimiento, String email, Roles roles, List<Reserva> reserva) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.contactoEmergencia = contactoEmergencia;
        this.contacto = contacto;
        this.NombreContactoEmergencia = NombreContactoEmergencia;
        this.parentesco = parentesco;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.roles = roles;
        this.reserva = reserva;
    }

  
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList();
        List<Roles> role = new ArrayList();
        role.add(roles);
        for (Roles rol : role) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rol.getName());
           authorityList.add(authority);
            
        }
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
         return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", username=" + username + ", password=" + password + ", contactoEmergencia=" + contactoEmergencia + ", contacto=" + contacto + ", NombreContactoEmergencia=" + NombreContactoEmergencia + ", parentesco=" + parentesco + ", fechaNacimiento=" + fechaNacimiento + ", email=" + email + ", roles=" + roles + ", reserva=" + reserva + '}';
    }

    
    
    
}
