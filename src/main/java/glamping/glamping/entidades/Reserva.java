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
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
    @ManyToOne(fetch = FetchType.EAGER)
   @JoinTable(
    name="ReservaCabaniaUsuario",
    joinColumns = @JoinColumn(name="Reserva_id"),
    inverseJoinColumns = @JoinColumn(name="Usuario_id")
    )
    private List<Usuario> usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
    name="ReservaCabaniaUsuario",
    joinColumns = @JoinColumn(name="Reserva_id"),
    inverseJoinColumns = @JoinColumn(name="Cabania_id")
    )
    private List<Cabania> cabania;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;

    public Reserva() {
    }


    public Reserva(Integer id, String nombre, List<Usuario> usuario, List<Cabania> cabania, LocalDate fechaInicio, LocalDate fechaFinal) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.cabania = cabania;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", cabania=" + cabania + ", fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal + '}';
    }

    

    
   
    
    

    
    
    
}
