/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.entidades;

/**
 *
 * @author Admin
 */
public class LoginResponseDTO {
    private Usuario usuario;
    private String jwt;
    public LoginResponseDTO(){
        super();
    }

    public LoginResponseDTO(Usuario usuario, String jwt) {
        this.usuario = usuario;
        this.jwt = jwt;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" + "usuario=" + usuario + ", jwt=" + jwt + '}';
    }
    
}
