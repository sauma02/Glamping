/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.servicios;

import java.time.Instant;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

/**
 *
 * @author laura
 */
@Service
public class TokenServicio {
    @Autowired
    private JwtEncoder encoder;
    @Autowired
    private JwtDecoder decoder;
    
    public String generateJwt(Authentication auth){
        //Para tomar un snapshot del tiempo al que se realice este metodo
        Instant now = Instant.now();
        //Se crea una nueva variable para almacenar las authorities y se llama de esta forma
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(""));
        
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles", scope)
                .build();
        
    
        
        
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
