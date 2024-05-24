/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.entidades.Usuario;
import glamping.glamping.servicios.EmailServicioImpl;

import glamping.glamping.servicios.PasswordResetTokenService;
import glamping.glamping.servicios.UsuarioServicio;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private PasswordResetTokenService passwordReset;
    
    

    @GetMapping("/login/forgotPassword")
    public String showForgotPasswordForm() {
        
             return "forgotPasswordForm.html";
        
      
        
    }

    @PostMapping("/login/forgotPassword/forgotPasswordReset")
    public String processForgotPasswordForm(@RequestParam("email") String email, ModelMap map) {
        try {
            
        
        Usuario usuario = usuarioServicio.encontrarPorEmail(email);
            System.out.println("Usuario: "+usuario);
        if (usuario != null) {
            // Generate and save password reset token
            String token = UUID.randomUUID().toString();
           LocalDateTime expirationTime = LocalDateTime.now().plusHours(2);
           passwordReset.createResetPasswordToken(email, token, expirationTime, usuario);

            // Send password reset email
            String resetToken = "Token: "+token;
            String emailBody = "Copia y pega este token en la seccion de resetear contraseña, "
                    + "encontraras un boton en donde ingresaste tu correo electronico que te "
                    + "redireccionara para que ingreses el token: "+token ;
           emailService.enviarMensajeSencillo(email, "Reinicio de contraseña", emailBody);
            map.addAttribute("token", token);
            map.addAttribute("exitoStatus", "success");
            map.addAttribute("exitoMensaje", "Token enviado a tu email: "+email);
            System.out.println("Token: "+token);
             return "forgotPasswordForm.html";
        }else{
            map.addAttribute("errorStatus", "inexistente");
            map.addAttribute("errorMensaje", "El correo ingresado no pertenece a ninguna cuenta");
            
            return "forgotPasswordForm.html";
            
        }
        } catch (Exception e) {
            e.printStackTrace();
            if(e.getCause() != null){
                System.err.println("Error: "+e.getCause().getMessage());
            }
            return "error.html";
        }
        
    }
}
