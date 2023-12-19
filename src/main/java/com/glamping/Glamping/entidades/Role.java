/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
//Se crea el modelo role y se implementa la interfaz grantedAuthority esto nos ayudara a dar accesos
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    @Column(name="rol_id")
    private String id;
    private String authority;

    public Role() {
    }

    public Role(String id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", authority=" + authority + '}';
    }
            
      @Override
    public String getAuthority() {
        return this.authority;
    }
    public void setAuthority(String authority){
        this.authority = authority;
    }
}
