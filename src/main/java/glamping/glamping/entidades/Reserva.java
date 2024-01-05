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
import jakarta.persistence.ManyToMany;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Setter
@Getter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="Reserva_id")
    private Integer id;
    private String nombre;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumns({
     @JoinColumn(name="Usuario_id", referencedColumnName="id"),
     @JoinColumn(name="Nombre de usuario", referencedColumnName="nombre")
    }
    )
    private Usuario usuario;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="Cabania_id", referencedColumnName="id")
    })
    private Cabania cabania;
    private Date fecha;

    public Reserva() {
    }

    public Reserva(Integer id, String nombre, Usuario usuario, Cabania cabania, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.cabania = cabania;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", cabania=" + cabania + ", fecha=" + fecha + '}';
    }
    
}
