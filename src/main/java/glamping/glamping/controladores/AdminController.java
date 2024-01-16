/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UsuarioServicio usuarioServicio;
  
    @GetMapping("/admin/listarUsuarios")
    public String listarUsuarios(Model modelo){
        modelo.addAttribute("Usuarios", usuarioServicio.listarUsuarios());
        return "listarUsuarios.html";
    }
}
