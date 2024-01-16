/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.config;

import glamping.glamping.entidades.Privilegios;
import glamping.glamping.entidades.Roles;
import glamping.glamping.entidades.Usuario;
import glamping.glamping.repositorios.PrivilegiosRepositorio;
import glamping.glamping.repositorios.RolesRepositorio;
import glamping.glamping.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;
    @Autowired
    private UsuarioRepositorio userRepository;
    @Autowired
    private RolesRepositorio roleRepository;
    @Autowired
    private PrivilegiosRepositorio privilegeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        Privilegios readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilegios writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        List<Privilegios> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
        Roles adminRole = roleRepository.findByName("ROLE_ADMIN");
        Usuario user = new Usuario();
        user.setNombre("Test");
        user.setUsername("Test");
        user.setPassword(passwordEncoder.encode("password"));
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        
        userRepository.save(user);
        alreadySetup = true;
    }

    @Transactional
    Privilegios createPrivilegeIfNotFound(String name) {
        Privilegios privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            Privilegios privilegio = new Privilegios();
            privilegio.setName(name);
            privilegeRepository.save(privilegio);
            
        }
        return privilege;
    }

    @Transactional
    Roles createRoleIfNotFound(String name, Collection<Privilegios> privileges) {
        Roles role = roleRepository.findByName(name);
        if (role == null) {
            role = new Roles();
            role.setName(name);
            role.setPrivilegios(privileges);
            roleRepository.save(role);
        }
        return role;
    }
    
}
