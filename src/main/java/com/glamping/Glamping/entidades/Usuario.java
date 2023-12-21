/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.entidades;

import com.glamping.Glamping.enumeraciones.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author USUARIO
 */
@Entity
//No se puede repeti el Email, lo establecemos desde la anotacion table
@Table(name= "usuario", uniqueConstraints ={@UniqueConstraint(columnNames = {"email", "username"})})
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="usuario_id")
    private Integer id;
    @Column(nullable = false)
    private String username;
    private String nombre;
    private Date fechaNacimiento;
    private String contactoEmergencia;
    private String ciudad;
    //Esta anotacion con este parametro no va a permitir ingresar nada en la base de datos si no esta el email
    @Column(nullable = false)
    private String email;
    private String password;
    //Se hace el fetch con esta anotacion para que apenas se cree el candidato se haga el fetch
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
    name="usuario_rol_junction",
     joinColumns = {@JoinColumn(name="usuario_id")},
     inverseJoinColumns = {@JoinColumn(name="rol_id")}
     
    )
    private Set<Role> authorities;

    public Usuario() {
        //Se crea constructor para que el role no llegue vacio
        super();
        this.authorities = new HashSet<Role>();
    }
    public Usuario(Integer id, String username, String password, Set<Role> authority){
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authority;
    }

    public Usuario(Integer id, String username, String nombre, Date fechaNacimiento, String contactoEmergencia, String ciudad, String email, String password, Set<Role> authorities) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.contactoEmergencia = contactoEmergencia;
        this.ciudad = ciudad;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

   

  
    public void setUsername(String username){
        this.username = username; 
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", contactoEmergencia=" + contactoEmergencia + 
                ", ciudad=" + ciudad + ", email=" + email + ", password=" + password + ", username=" + username + '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
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
    
    
    
}
