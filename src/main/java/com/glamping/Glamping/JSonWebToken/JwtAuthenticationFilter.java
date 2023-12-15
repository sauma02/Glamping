/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.JSonWebToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Admin
 */
@Component
//Se extiende a la clase abstract definida, la cual se utiliza para crear filtros personalizados
//se extiende a esta clase para verificar que solo se haga 1 verificacion 1 a 1 por filtro en el https
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Este metodo va a realizar todos los filtros relacionados al token
        //Se crea variable final token que reciba el token del request 
        final String token = getTokenFromRequest(request);
        //Si el token es nulo se le devuelve a la cadena de filtros el control
        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }
//Se crea el metodo que devuelva un string ya que el token es una cadena
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")){
            //Se extrae el token del authHeader, y se retorna sino se retorna un numero
            return authHeader.substring(7);
        }
        return null;
    }
    
}
