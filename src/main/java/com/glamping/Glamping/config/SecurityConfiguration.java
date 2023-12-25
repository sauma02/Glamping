/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.config;

import com.glamping.Glamping.util.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Admin
 */
@Configuration
public class SecurityConfiguration {
    
    private final RSAKeyProperties keys;
    //Se establece el securitychain
    //Se crea el metodo con la anotacion bean para la verificacion de usuarios

    public SecurityConfiguration(RSAKeyProperties keys) {
        this.keys = keys;
    }
    
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    //Esto facilita que el programa acceda al userdetailservice de UsuarioServicio y de esta manera proceder con la verificacion
    public AuthenticationManager authManager(UserDetailsService userDetail){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetail);
        //Se establece el autenticador, con el user detail para que devuelva un manager
        return new ProviderManager(dao);
    }
    @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         return http
                 .csrf(csrf->csrf.disable())
                 //Se establece este authorizeHttpRequests y el auth que apunte a permitir cualquier llamado
                 //De esta manera se podra acceder a la vista general
                 //Ahora se reemplaza con un authenticated para que solo las personas logueadas puedan ingresar
                 .authorizeHttpRequests(auth -> {
                     //Se establece un requestMacthers para dar los accesos a las url
                        //en este caso todo lo relacionado con los auth/**
                 auth.requestMatchers("/auth/**").permitAll();
                 auth.anyRequest().authenticated();
                 })      
                 //Se establece el httpBasic para que se logueen por form
                 //.httpBasic().and(),  se cambia a oauth2 resource server para 
                 //la uatenticacion por token, se establece el auto2 configurer
                 //con el parametro:: de jwt
                 .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                 //Lo configuramos para que sea stateless
                 .sessionManagement(session -> 
                         session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .build();
     }
     //Vamos a añadir un metodo con la anotacion bean para hacer la decodificacion
     @Bean
     //Creamos un jwtdecoder para hacer las decodificaciones
     public JwtDecoder jwtDecoder(){
         //Retornamos un nimbus public decoder para la public key
         return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
         
     }
     @Bean
     //Crearmos un jwtencoder para codificar
     public JwtEncoder jwtEncoder(){
         //Se crea un JWK usando las keys publicas y privadas
        JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
         JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
         //Se devuelve el nimbus encoder
         return new NimbusJwtEncoder(jwks);
     }
}
