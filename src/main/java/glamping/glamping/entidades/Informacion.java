/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter

public class Informacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String titulo;
    private String seccion;
    private String texto;
   @OneToOne(mappedBy="info")
    private Imagen imagen;

    public Informacion(Integer id, String titulo, String texto, String seccion , Imagen imagen) {
        this.id = id;
        this.titulo = titulo;
        this.seccion = seccion;
        this.texto = texto;
        this.imagen = imagen;
    }

    public Informacion() {
    }

    @Override
    public String toString() {
        return "Informacion{" + "id=" + id + ", titulo=" + titulo + ", texto=" + texto + ", imagen=" + imagen + '}';
    }
   
    
    
}
