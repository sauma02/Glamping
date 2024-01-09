/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.config;

import com.glamping.Glamping.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Admin
 */
@Configuration
public class SecurityConfig {
    @Autowired
    public UsuarioServicio usuarioServicio;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception{
        return https.formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(req -> req
                .requestMatchers("/logjn/**").permitAll()
                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/usuario/").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated())
               .userDetailsService(usuarioServicio).build();
    }
}
