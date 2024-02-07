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
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Cabania {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="Cabania_id")
    private Integer id;
    private String nombre;
    private Integer capacidad;
    private String imagen;
    @ManyToMany(mappedBy = "cabania")
    private List<Reserva> reserva;
    private boolean estado;

    public Cabania() {
    }

    public Cabania(Integer id, String nombre, Integer capacidad, String imagen, List<Reserva> reserva, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.imagen = imagen;
        this.reserva = reserva;
        this.estado = estado;
    }

    

    @Override
    public String toString() {
        return "Cabania{" + "id=" + id + ", nombre=" + nombre + ", capacidad=" + capacidad + ", reserva=" + reserva + ", estado=" + estado + '}';
    }

    
    
    
    
}
