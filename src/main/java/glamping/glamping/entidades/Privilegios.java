/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
public class Privilegios {
   @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Roles> roles;

    public Privilegios() {
    }

   

    public Privilegios(Integer id, String name, Collection<Roles> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Privilegios{" + "id=" + id + ", name=" + name + ", roles=" + roles + '}';
    }
    
}
