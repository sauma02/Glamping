/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.JSonWebToken.config;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class RegisterRequest {
    String nombre;
    String email;
    String contactoEmergencia;
    Date fechaNacimiento;
    String ciudad;
    String password;

    public RegisterRequest() {
    }

    public RegisterRequest(String nombre, String email, String contactoEmergencia, Date fechaNacimiento, String ciudad,  String password) {
        this.nombre = nombre;
        this.email = email;
        this.contactoEmergencia = contactoEmergencia;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    @Override
    public String toString() {
        return "RegisterRequest{" + "nombre=" + nombre + ", email=" + email + ", contactoEmergencia=" + contactoEmergencia + ", fechaNacimiento=" + fechaNacimiento + ", ciudad=" + ciudad + '}';
    }
    
}
