/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.repositorios.CabaniaRepositorio;
import glamping.glamping.repositorios.ReservaRepositorio;
import glamping.glamping.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Admin
 */
@Controller
public class ReservaController {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
     @Autowired
    private CabaniaRepositorio cabaniaRepositorio;
      @Autowired
    private ReservaRepositorio reservaRepositorio;
}
