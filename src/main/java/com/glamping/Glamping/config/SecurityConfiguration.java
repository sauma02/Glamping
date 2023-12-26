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
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
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
         String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("sha256", new StandardPasswordEncoder());

        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
        return passwordEncoder;
        
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
            http.csrf(csrf->csrf.disable())
                 //Se establece este authorizeHttpRequests y el auth que apunte a permitir cualquier llamado
                 //De esta manera se podra acceder a la vista general
                 //Ahora se reemplaza con un authenticated para que solo las personas logueadas puedan ingresar
                 .authorizeHttpRequests(auth -> {
                     //Se establece un requestMacthers para dar los accesos a las url
                        //en este caso todo lo relacionado con los auth/**
                 auth.requestMatchers("/auth/**").permitAll();
                 auth.requestMatchers("/admin/**").hasRole("ADMIN");
                 auth.requestMatchers("/inicio/**").hasAnyRole("ADMIN", "USUARIO");
                 
                 auth.anyRequest().authenticated();
                 });      
                 //Se establece el httpBasic para que se logueen por form
                 //.httpBasic().and(),  se cambia a oauth2 resource server para 
                 //la uatenticacion por token, se establece el auto2 configurer
                 //con el parametro:: de jwt
            http.oauth2ResourceServer()
                 .jwt()
                 .jwtAuthenticationConverter(jwtAuthConverter());
                 //Lo configuramos para que sea stateless
            http.sessionManagement(session -> 
                         session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                 return http.build();
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
     @Bean
     //Se crea para dar diferentes permisos
     public JwtAuthenticationConverter jwtAuthConverter(){
         //Se renombra los roles para ponerle los prefijos
         //Para hacer los matchs y decodes directamente se le pone el ROLE_ 
         //para que spring security pueda interpretar los permisos
         JwtGrantedAuthoritiesConverter jwtAuthGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
         jwtAuthGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
         jwtAuthGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
         JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
         jwtConverter.setJwtGrantedAuthoritiesConverter(jwtAuthGrantedAuthoritiesConverter);
         return jwtConverter;
       
     }
}
