/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.servicios;

import glamping.glamping.entidades.PasswordResetToken;
import glamping.glamping.entidades.Usuario;
import glamping.glamping.repositorios.PasswordResetTokenRepository;
import glamping.glamping.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository passwordResetRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void createResetPasswordToken(String email, String token, LocalDateTime expirationTime) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(email, token, expirationTime);
        passwordResetRepo.save(passwordResetToken);
    }

    public String validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> optionalToken = passwordResetRepo.findByToken(token);
        System.out.println("Token: "+optionalToken);
        if (optionalToken.isPresent()) {
            PasswordResetToken passwordToken = optionalToken.get();
            if (passwordToken.isExpired(passwordToken.getExpirationDate())) {
                passwordResetRepo.delete(passwordToken);
                return null;
            } else {
                return passwordToken.getEmail();
            }
        }
        return null;
    }

    public void resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> passwordToken = passwordResetRepo.findByToken(token);
        if (passwordToken.isPresent()) {
            PasswordResetToken passwordTok = passwordToken.get();
            Usuario usuario = usuarioServicio.encontrarPorEmail(passwordTok.getEmail());
            if(usuario != null){
                usuario.setPassword(passwordEncoder.encode(newPassword));
                usuarioRepositorio.save(usuario);
                
            }
           
            passwordResetRepo.delete(passwordTok);

        }
    }

}
