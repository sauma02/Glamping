/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.glamping.Glamping.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 *
 * @author laura
 */
public class KeyGeneratorUtility {
    public static KeyPair generateRsaKey(){
        //procedemos a codificar la key
        KeyPair keyPair;
        try {
            //Se crear un keypair generator con la instance de rsa para la codificacion de keys
            KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
            //Se utilizar 2048 bits para la inicializacion del generador
            generador.initialize(2048);
            keyPair = generador.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException();
            
        }
        
        return keyPair; 
    }
}
