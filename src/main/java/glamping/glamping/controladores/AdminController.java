/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.controladores;

import glamping.glamping.config.FileUploadUtil;
import glamping.glamping.entidades.Cabania;
import glamping.glamping.entidades.Imagen;
import glamping.glamping.entidades.Informacion;
import glamping.glamping.entidades.Reserva;

import glamping.glamping.entidades.ResponseMessage;
import glamping.glamping.entidades.Roles;
import glamping.glamping.entidades.Usuario;
import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.servicios.CabaniaServicio;
import glamping.glamping.servicios.FileStorageService;
import glamping.glamping.servicios.ImagenServicio;
import glamping.glamping.servicios.InformacionServicio;
import glamping.glamping.servicios.ReservaServicio;
import glamping.glamping.servicios.RolesServicio;
import glamping.glamping.servicios.UsuarioServicio;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author USUARIO
 */
@Controller
public class AdminController {

    @Autowired
    private CabaniaServicio cabaniaServicio;
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private ImagenServicio imagenServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ReservaServicio reservaServicio;
    @Autowired
    private RolesServicio rolServicio;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private InformacionServicio infoServicio;

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<Informacion> listaInfo = infoServicio.listarInformacion();

        model.addAttribute("listaInfo", listaInfo);
        String username = userDetails.getUsername();

        model.addAttribute("nombreUsuario", username);

        return "admin.html";
    }

    @GetMapping("/admin/panelDeManejo")
    public String panelDeManejoLista(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        List<Informacion> listaInfo = infoServicio.listarInformacion();
        model.addAttribute("infoLista", listaInfo);
        model.addAttribute("nombreUsuario", username);
        return "panelDeManejo.html";
    }

    @GetMapping("/admin/panelDeManejo/registrarInfo")
    public String crearInfoForm() {
        return "registrarInfo.html";
    }

    @PostMapping("/admin/panelDeManejo/registrarInfo/registrar")
    public String submitRegistroForm(@RequestParam("titulo") String titulo, @RequestParam("iconoServicio") String iconoServicio,
            @RequestParam("texto") String texto, @RequestParam("seccion") String seccion,
            @RequestParam("imagen") MultipartFile imagen, ModelMap map) {
        try {
            if (imagen == null) {
                List<Informacion> listaInfo = infoServicio.listarInformacion();

                map.addAttribute("listaInfo", listaInfo);
                infoServicio.crearNuevaInfo(titulo, iconoServicio, texto, seccion, null);
                map.addAttribute("Exito", "success");
                map.addAttribute("exitoMensaje", "Exito al crear Informacion");
                return "registrarInfo.html";
            } else {
                List<Informacion> listaInfo = infoServicio.listarInformacion();
                map.addAttribute("Exito", "success");
                map.addAttribute("exitoMensaje", "Exito al crear Informacion");
                map.addAttribute("listaInfo", listaInfo);
                storageService.init();
                infoServicio.crearNuevaInfo(titulo, iconoServicio, texto, seccion, imagen);
                return "registrarInfo.html";
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
            return "error.html";
        }

    }

    @GetMapping("/admin/panelDeManejo/registrarInfo/editarInfo/{id}")
    public String editarInfoForm(@PathVariable("id") Integer id, ModelMap map) {
        List<Informacion> listaInfo = infoServicio.listarInformacion();

        map.addAttribute("listaInfo", listaInfo);
        Informacion info = infoServicio.buscarPorId(id);
        map.addAttribute("informacion", info);
        return "editarPagina.html";
    }

    @PostMapping("/admin/panelDeManejo/registrarInfo/editarInfo/editar")
    public String editarAccionInfo(@RequestParam("id") Integer id, @RequestParam("titulo") String titulo, @RequestParam("iconoServicio") String iconoServicio,
            @RequestParam("texto") String texto, @RequestParam("seccion") String seccion, @RequestParam("imagen") MultipartFile imagen, ModelMap map) {
        try {
            if (imagen.isEmpty()) {
                List<Informacion> listaInfo = infoServicio.listarInformacion();

                map.addAttribute("listaInfo", listaInfo);
                Informacion info = infoServicio.buscarPorId(id);
                Imagen img = info.getImagen();
                info.setTexto(texto);
                info.setTitulo(titulo);
                info.setImagen(img);
                info.setSeccion(seccion);
                info.setIconoServicio(iconoServicio);
                infoServicio.editarInfo(info);
                 map.addAttribute("exitoStatus", "success");
               map.addAttribute("exitoMensaje", "Exito al editar informacion");
                return "redirect:/admin/panelDeManejo";
            } else {
                List<Informacion> listaInfo = infoServicio.listarInformacion();

                map.addAttribute("listaInfo", listaInfo);
                storageService.init();

                storageService.save(imagen);
                Imagen img = new Imagen();
                img.setFileName(storageService.listOneFile(imagen).getOriginalFilename());
                Informacion info = infoServicio.buscarPorId(id);
                imagenServicio.guardarImagenInfo(info, img, imagen);
                info.setImagen(img);
                info.setTexto(texto);
                info.setTitulo(titulo);
                info.setSeccion(seccion);
                info.setIconoServicio(iconoServicio);
                 map.addAttribute("exitoStatus", "success");
               map.addAttribute("exitoMensaje", "Exito al editar informacion");
                infoServicio.editarInfo(info);
                 return "redirect:/admin/panelDeManejo";

            }
        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
            List<Informacion> listaInfo = infoServicio.listarInformacion();

            map.addAttribute("listaInfo", listaInfo);
            Informacion info = infoServicio.buscarPorId(id);
            map.addAttribute("informacion", info);
            map.addAttribute("errorException", e);
            System.out.println("Error exception: " + e);
            Imagen img = new Imagen();
            img.setFileName(storageService.listOneFile(imagen).getOriginalFilename());
            map.addAttribute("imgDuplicada", img);
            map.addAttribute("errorDuplicado", "La imagen que intentas registrar ya existe");
            return "editarPagina.html";
        }
    }

    @PostMapping("/admin/panelDeManejo/registrarInfo/registrar/eliminar/{id}")
    public String eliminarInfo(@PathVariable("id") Integer id, Model model) {
        try {
            List<Informacion> listaInfo = infoServicio.listarInformacion();

            model.addAttribute("listaInfo", listaInfo);
            Informacion info = infoServicio.buscarPorId(id);
            Imagen img = info.getImagen();
            imagenServicio.eliminarImagen(img);
            infoServicio.eliminarInfo(id);
            return "redirect:/admin/panelDeManejo";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
            return "error.html";
        }
    }

    @GetMapping("/admin/usuarios/crearUsuarioAdmin")
    public String registrarUsuarioAdmin(Model model) {
        try {
            List<Informacion> listaInfo = infoServicio.listarInformacion();

            model.addAttribute("listaInfo", listaInfo);
            return "registrarUsuarioAdmin";
        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
            return "error.html";
        }
    }

    @PostMapping("/admin/usuarios/crearUsuarioAdmin/crear")
    public String registrarUserAdminForm(@ModelAttribute("user") Usuario user, @RequestParam("nombre") String nombre,
            @RequestParam("username") String username, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("contacto") String contacto,
            @RequestParam("contactoEmergencia") String contactoEmergencia, @RequestParam("nombreContactoEmergencia") String nombreContactoEmergencia,
            @RequestParam("parentesco") String parentesco,
            @RequestParam("fechaNacimiento") @DateTimeFormat(pattern = "dd/mm/yyyy") LocalDate fechaNacimiento,
            @RequestParam("rol") String rol, ModelMap map) {
        try {
            List<Informacion> listaInfo = infoServicio.listarInformacion();

            map.addAttribute("listaInfo", listaInfo);
            Usuario verificarEmail = usuarioServicio.encontrarPorEmail(email);
            Usuario verificarUsuario = usuarioServicio.encontrarPorUsername(username);
            if (verificarEmail != null) {

                map.addAttribute("errorEmail", "El email ya esta en uso");
                System.out.println("Existe");
            }
            if (verificarUsuario != null) {
                map.addAttribute("errorUsuario", "El nombre de usuario ya esta en uso");
                System.out.println("Existe el usuario");

            }

            if (verificarUsuario == null && verificarEmail == null) {
                usuarioServicio.crearUsuarioAdmin(nombre, username, passwordEncoder.encode(password),
                        contacto, contactoEmergencia, nombreContactoEmergencia, parentesco, email, fechaNacimiento, rol);
                map.addAttribute("Exito", "Usuario creado con exito");
                return "redirect:/admin/usuarios";
            }
            return "redirect:/admin/usuarios";
        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
            return "error.html";
        }
    }

    @GetMapping("/admin/registrarCabañas")
    public String registrarCabania(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        List<Informacion> listaInfo = infoServicio.listarInformacion();

        model.addAttribute("listaInfo", listaInfo);
        model.addAttribute("nombreUsuario", username);
        return "registrarCabania.html";
    }

    @PostMapping("/admin/registrarCabañas/registrarCabaña")
    public String registerForm(@RequestParam("nombre") String nombre,
            @RequestParam("capacidad") Integer capacidad, @RequestParam("descripcion") String descripcion, @RequestParam("imagen") MultipartFile imagen, @RequestParam("estado") boolean estado, Model map) throws MiExcepcion, IOException, Exception {
        String mensaje = "";
        try {
            List<Informacion> listaInfo = infoServicio.listarInformacion();

            map.addAttribute("listaInfo", listaInfo);
            Cabania cab = cabaniaServicio.listarCabaniaPorNombre(nombre);
            if (cab != null) {
                map.addAttribute("errorCabaniaExistente", "La cabaña con el nombre " + nombre + " ya existe");
                map.addAttribute("estado", estado);
                map.addAttribute("capacidad", capacidad);
                mensaje = "la cabaña indicada ya existe";
                return "registrarCabania.html";
            }
            if (imagen == null || imagen.isEmpty()) {
                map.addAttribute("imagenVacia", "Imagen nula");
                map.addAttribute("exitoStatus", "Error");
                map.addAttribute("exitoCabania", "Error al crear cabaña");
                throw new Exception("Error, la imagen es nula, no puede ser nula");

            } else {
                // Manejar la carga de la imagen y guardarla
                Cabania cabania = new Cabania();
                Imagen img = new Imagen();
                storageService.init();

                // Establecer los demás atributos de la cabaña
                cabania.setNombre(nombre);
                cabania.setCapacidad(capacidad);
                cabania.setEstado(estado);
                cabania.setDescripcion(descripcion);
                storageService.save(imagen);
                img.setFileName(storageService.listOneFile(imagen).getOriginalFilename());
                // Asignar la imagen a la lista de imágenes de la cabaña
                List<Imagen> listaImagenes = new ArrayList<>();
                listaImagenes.add(img);
                cabania.setImagen(listaImagenes);

                // Guardar la cabaña en la base de datos
                cabaniaServicio.crearCabania(cabania);
                imagenServicio.guardarImagen(cabania, img, imagen);

                // Informar sobre el éxito del registro
                map.addAttribute("exitoStatus", "Exito");
                map.addAttribute("exitoCabania", "Éxito al crear cabaña");
                mensaje = "admin.html";
                return mensaje;
            }

        } catch (SQLException e) {
            throw new Exception("Error en la creacion de la cabaña", e.getCause());

        }
    }

    @GetMapping("/admin/verCabanias")
    public String listarCabanias(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        List<Informacion> listaInfo = infoServicio.listarInformacion();

        model.addAttribute("listaInfo", listaInfo);
        List<Cabania> listaCabanias = cabaniaServicio.listarCabanias();
        for (Cabania cab : listaCabanias) {
            model.addAttribute("cabania", cab);
        }
        model.addAttribute("listaCabanias", listaCabanias);
        model.addAttribute("nombreUsuario", username);
        return "listarCabañas.html";
    }

    @GetMapping("/admin/cabania/editarCabaña/{id}")
    public String editarCabana(@AuthenticationPrincipal UserDetails userDetails, Model model, @PathVariable Integer id) {
        Cabania cabania = cabaniaServicio.listarCabaniaPorId(id);
        model.addAttribute("cabana", cabania);
        return "editarCabana.html";
    }

    @GetMapping("/admin/cabania/aniadirImagenes/{id}")
    public String editarImagenes(@AuthenticationPrincipal UserDetails userDetails, Model model, @PathVariable Integer id) {
        Cabania cabania = cabaniaServicio.listarCabaniaPorId(id);
        List<Imagen> img = cabania.getImagen();
        model.addAttribute("cabana", cabania);
        model.addAttribute("imagenes", img);
        return "añadirImagenes.html";
    }

    @PostMapping("/admin/cabania/aniadirImagenes/editar/{id}")
    public String editarImagenCabana(@RequestParam("imagen") MultipartFile imagen, @PathVariable Integer id) {
        try {
            Imagen img = imagenServicio.imagenPorId(id);
            Cabania cabania = img.getCabania();
            storageService.init();
            if (imagen.isEmpty()) {
                System.err.println("Imagen vacia");
                return "redirect:/admin/verCabanias";
            }
            if (storageService.listOneFile(imagen) == imagen) {
                storageService.delete2(imagen);

            } else if (storageService.listOneFile(imagen).isEmpty()) {
                return "redirect:/admin/verCabanias";
            }

            List<Imagen> imagenes = cabania.getImagen();
            for (Imagen imag : imagenes) {
                if (img == imag) {
                    storageService.delete(imag);
                    storageService.save(imagen);
                    imag.setFileName(storageService.listOneFile(imagen).getOriginalFilename());
                    imagenServicio.guardarImagen(cabania, imag, imagen);
                    imagenes.remove(imag);
                    imagenes.add(img);
                }
            }
            cabaniaServicio.editarImagenes(cabania);

            return "redirect:/admin/verCabanias";
        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
            return "error.html";
        }

    }

    @PostMapping("/admin/cabania/aniadirImagenes/aniadir")
    public String añadirImagenesForm(@RequestParam("id") Integer id, @RequestParam("imagen[]") MultipartFile[] imagen) {
        try {
            storageService.init();
            Cabania cabania = cabaniaServicio.listarCabaniaPorId(id);
            Imagen img = new Imagen();
            List<Imagen> listaImagenes = cabania.getImagen();
            if (listaImagenes == null || listaImagenes.isEmpty()) {
                List<Imagen> imgList = new ArrayList<>();
                cabania.setImagen(imgList);

                for (MultipartFile file : imagen) {
                    if (storageService.listOneFile(file).isEmpty() || storageService.listOneFile(file) == null) {
                        img.setFileName(storageService.listOneFile(file).getOriginalFilename());
                        // Asignar la imagen a la lista de imágenes de la cabaña

                        listaImagenes.add(img);

                        storageService.save(file);
                    }
                    if (file == storageService.listOneFile(file)) {
                        MultipartFile file2 = storageService.listOneFile(file);
                        storageService.delete2(file2);
                        img.setFileName(storageService.listOneFile(file).getOriginalFilename());
                        // Asignar la imagen a la lista de imágenes de la cabaña

                        listaImagenes.add(img);
                        storageService.save(file);

                    }
                }
            } else {
                for (MultipartFile file : imagen) {
                    if (storageService.listOneFile(file).isEmpty() || storageService.listOneFile(file) == null) {
                        img.setFileName(storageService.listOneFile(file).getOriginalFilename());
                        // Asignar la imagen a la lista de imágenes de la cabaña

                        listaImagenes.add(img);

                        storageService.save(file);
                    }
                    if (file == storageService.listOneFile(file)) {
                        MultipartFile file2 = storageService.listOneFile(file);
                        storageService.delete2(file2);
                        img.setFileName(storageService.listOneFile(file).getOriginalFilename());
                        // Asignar la imagen a la lista de imágenes de la cabaña

                        listaImagenes.add(img);
                        storageService.save(file);

                    }

                }
            }

            cabania.setImagen(listaImagenes);
            cabaniaServicio.añadirImagenes(cabania, imagen);
            // Informar sobre el éxito del registro

            return "redirect:/admin/verCabanias";

        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
            return "error.html";
        }
    }

    @PostMapping("/admin/cabania/añadirImagenes/eliminar/{id}")
    public String eliminarImagenesForm(@PathVariable Integer id) {
        try {
            cabaniaServicio.eliminarImagen(id);
            return "redirect:/admin/verCabanias";

        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
            return "error.html";

        }
    }

    @PostMapping("/admin/cabania/editarCabaña/editar")
    public String editarCabanaForm(@ModelAttribute Cabania cabania, String nombre, String descripcion, Integer capacidad) {
        try {

            cabaniaServicio.editarCabana(cabania.getId(), nombre, descripcion, capacidad);
            return "redirect:/admin/verCabanias";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() != null) {
                System.err.println("Cause: " + e.getCause().getMessage());
            }
            return "error.html";
        }
    }

    @GetMapping("/admin/usuarios")
    public String usuarios(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();

        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        List<Reserva> reservas = reservaServicio.listarReservas();
        model.addAttribute("reserva", reservas);

        model.addAttribute("nombreUsuario", username);
        return "listarUsuarios.html";
    }

    @GetMapping("/admin/usuarios/editarUsuario/{id}")
    public String editarUsuario(@AuthenticationPrincipal UserDetails userDetails, Model model, @PathVariable Integer id) {
        Usuario us = usuarioServicio.listarUsuarioPorId(id);
        model.addAttribute("usuario", us);
        String username = userDetails.getUsername();
        model.addAttribute("nombreUsuario", username);
        return "editarUsuario.html";

    }

    @PostMapping("/admin/usuarios/editarUsuario/editar")
    public String editarUsuarioForm(@ModelAttribute Usuario usuario, String username, String nombre, boolean estado, String rol, String password,
            String contacto, String contactoEmergencia, String nombreContactoEmergencia, String parentesco, String email, LocalDate fechaNacimiento, Model model) throws MiExcepcion, Exception {

        try {
            model.addAttribute("exitoStatus", "success");
            model.addAttribute("exitoMensaje", "Exito al editar Usuario");
            usuarioServicio.editar(usuario.getId(), nombre, username, password, contacto, contactoEmergencia, nombreContactoEmergencia, parentesco, email, rol, fechaNacimiento);
            return "listaCabañas.html";
        } catch (Exception e) {
            // Log the complete stack trace
            e.printStackTrace();

            // You can also log the cause of the exception if available
            if (e.getCause() != null) {
                System.err.println("Cause: " + e.getCause().getMessage());
            }

            // You can return an error page or redirect the user to an error page
            return "error.html";
        }

    }

    @PostMapping("/admin/verCabanias/{id}")
    public String cambiarEstado(@PathVariable Integer id, @RequestParam("estado") boolean estado) {
        try {
            cabaniaServicio.cambiarEstado(id, estado);
            return "redirect:/admin/verCabanias";
        } catch (Exception e) {
            e.printStackTrace();

            // You can also log the cause of the exception if available
            if (e.getCause() != null) {
                System.err.println("Cause: " + e.getCause().getMessage());
            }

            // You can return an error page or redirect the user to an error page
            return "error.html";
        }
    }

    @PostMapping("/admin/verCabanias/eliminar/{id}")
    public String eliminarCabana(@PathVariable Integer id) {
        try {
            cabaniaServicio.eliminarCabaniaPorId(id);
            return "redirect:/admin/verCabanias";
        } catch (Exception e) {
            e.printStackTrace();

            // You can also log the cause of the exception if available
            if (e.getCause() != null) {
                System.err.println("Cause: " + e.getCause().getMessage());
            }

            // You can return an error page or redirect the user to an error page
            return "error.html";
        }
    }

    @PostMapping("/admin/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        try {
            usuarioServicio.eliminarUsuario(id);
            return "redirect:/admin/usuarios";

        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
            return "Error";
        }
    }
}
