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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Admin
 */
@Entity

@Builder
@Getter
@Setter
@AllArgsConstructor     
@NoArgsConstructor   
@Table(name = "imagenesCabaña")        
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    
    @Column(name="imagen_id")
    private Integer id;
    private String fileName;
    private String fileType;
    private String ruta;
   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Cabania_id")
    private Cabania cabania;
    private Long tamano;
  
   
    

  

   
    
}
