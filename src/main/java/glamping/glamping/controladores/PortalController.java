/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
public class PortalController {
    @GetMapping("/")
    public String inicio(){
        return "inicio.html";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin.html";
    }
    @GetMapping("/usuario")
    public String usuario(){
        return "usuario.html";
    }
}
