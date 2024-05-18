/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.entidades.PasswordResetToken;
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
    @GetMapping("/login/forgotPassword/forgotPasswordReset/resetPassword={token}")
    public String resetPasswordForm(@PathVariable("token") String token, ModelMap map){
        try {
            String email = passwordTokenService.validatePasswordResetToken(token);
        if(email != null){
            map.addAttribute("token", token);
            return "resetPasswordForm.html";
        }else{
            return "redirect:/forgot-password?=invalidToken";
        }
        } catch (Exception e) {
            e.printStackTrace();
            
            if(e.getCause() != null){
                System.err.println("Error: "+e.getCause().getMessage());
            }
            return "error.html";
        }
        
    }
    @PostMapping("/login/forgotPassword/forgotPasswordReset/resetPasswordresetPasswordReset")
    public String processResetPasswordForm(@RequestParam("token") String token, @RequestParam("password") String password){
        try {
            
            passwordTokenService.resetPassword(token, password);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            
            if(e.getCause() != null){
                System.err.println("Error: "+e.getCause().toString());
            }
            return "error.html";
        }
    }
}