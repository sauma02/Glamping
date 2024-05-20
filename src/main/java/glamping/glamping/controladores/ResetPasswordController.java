/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.entidades.PasswordResetToken;
import glamping.glamping.entidades.Usuario;

import glamping.glamping.servicios.PasswordResetTokenService;
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
public class ResetPasswordController {
    @Autowired
    private PasswordResetTokenService passwordTokenService;
  
    @GetMapping("/login/forgotPassword/forgotPasswordReset/resetPassword")
    public String resetPasswordForm(ModelMap map){
        try {
      
           
            
            return "resetPasswordForm.html";
       
          
       
        } catch (Exception e) {
            e.printStackTrace();
            
            if(e.getCause() != null){
                System.err.println("Error: "+e.getCause().getMessage());
            }
            return "error.html";
        }
        
    }
    @PostMapping("/login/forgotPassword/forgotPasswordReset/resetPasswordresetPasswordReset")
    public String processResetPasswordForm(@RequestParam("token") String token, @RequestParam("password") String password, ModelMap map){
        try {
            String validar = passwordTokenService.validatePasswordResetToken(token);
            if(validar != null){
                 PasswordResetToken passToken = passwordTokenService.findByToken(token);
            Usuario us = passToken.getUsuario();
            String passwordVieja = us.getPassword();
            if(passwordVieja.equals(password)){
                map.addAttribute("errorStatus", "duplicate");
                map.addAttribute("errorMensaje", "La contraseña nueva no puede ser igual a la anterior");
                return "resetPasswordForm.html"; 
            }else{
             passwordTokenService.resetPassword(token, password);
             map.addAttribute("exitoStatus", "success");
             map.addAttribute("exitoMensaje", "Contraseña cambiada con exito");
                return "resetPasswordForm.html"; 
            }
            }else{
             map.addAttribute("errorStatus", "expiro");
             map.addAttribute("errorMensaje", "EL token expiro vuelva al login y solicite uno nuevo");
                return "resetPasswordForm.html"; 
            }
           
            
        } catch (Exception e) {
            e.printStackTrace();
            
            if(e.getCause() != null){
                System.err.println("Error: "+e.getCause().toString());
            }
            return "error.html";
        }
    }
}
