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
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
public class Roles {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    @Column(name="Rol_id")
    private Integer id;
    @Column(name="nombre")
    private String name;

    
 
    public Roles() {
    }

    public Roles(Integer id, String name) {
        this.id = id;
        this.name = name;
     
    }

    @Override
    public String toString() {
        return "Roles{" + "id=" + id + ", name=" + name + '}';
    }



   
  

    
    
}
