/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.entidades.Usuario;
import glamping.glamping.servicios.EmailServicioImpl;
import glamping.glamping.servicios.UsuarioServicio;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class ForgotPasswordController {
     @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private EmailServicioImpl emailService;
    

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgotPasswordForm";
    }

    @PostMapping("/forgot-password")
    public String processForgotPasswordForm(@RequestParam("email") String email) {
        Usuario usuario = usuarioServicio.encontrarPorEmail(email);
        if (usuario != null) {
            // Generate and save password reset token
            String token = UUID.randomUUID().toString();
            usuario.setResetToken(token);
            usuario.setResetTokenExpiry(LocalDateTime.now().plusHours(24)); // Token expires in 24 hours
            usuarioServicio.save(usuario);

            // Send password reset email
            String resetPasswordLink = "http://yourdomain.com/reset-password?token=" + token;
            String emailBody = "Click the link below to reset your password:\n" + resetPasswordLink;
            emailService.sendEmail(email, "Password Reset", emailBody);
        }
        return "redirect:/forgot-password?success";
    }
}
