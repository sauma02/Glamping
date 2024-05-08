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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String token;
    private String email;
    @OneToOne
    private Usuario usuario;
    private LocalDateTime expirationDate;
    public PasswordResetToken(){
        this.token = UUID.randomUUID().toString();
        this.email = email;
        this.expirationDate = calculateExpirationDate();
    }
     public PasswordResetToken(String email, String token, LocalDateTime expirationDate){
        this.token = token;
        this.email = email;
        this.expirationDate = calculateExpirationDate();
    }
    public PasswordResetToken(Usuario usuario){
        this.usuario = usuario;
        this.email = email;
        this.token = UUID.randomUUID().toString();
        this.expirationDate = calculateExpirationDate();
    }
    private LocalDateTime calculateExpirationDate(){
        return LocalDateTime.now().plusDays(1);
    }
    public boolean isExpired(LocalDateTime expirationDate){
        return expirationDate.isBefore(LocalDateTime.now());
    }
}
