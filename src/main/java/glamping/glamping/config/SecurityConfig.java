/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.config;


import glamping.glamping.excepciones.MiExcepcion;
import glamping.glamping.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


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
        try {
        return https.formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(req -> req
                .requestMatchers("/").permitAll()
                .requestMatchers("/" , "/js/**", "/css/**").permitAll()
                .requestMatchers("/templates/**").permitAll()
                .requestMatchers("/img/**").permitAll()
                .requestMatchers("/cabania/**").permitAll()
                .requestMatchers("/register/**").permitAll()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/login/reset-password/**").permitAll()
                .requestMatchers("/admin/**").hasAnyAuthority("admin")
                .requestMatchers("/usuario/**").hasAnyAuthority("admin", "usuario")
                .anyRequest().authenticated())
                .formLogin()
                .loginPage("/login")
                .successHandler(myAuthenticationSuccessHandler()).permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").and()
                .userDetailsService(usuarioServicio).build();    
        } catch (MiExcepcion e) {
            throw new MiExcepcion("Error");
        }
        
    }
     @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authentication -> authentication;
    }
    @Bean
    //Se crea el bean nuevo para poder redireccionar basado en el rol de la persona
    public MySimpleUrlAuthenticationSuccessHandler myAuthenticationSuccessHandler(){
            return new MySimpleUrlAuthenticationSuccessHandler();
    }
    
    //https://www.baeldung.com/spring-redirect-after-login
}
