/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.entidades;

/**
 *
 * @author Admin
 */
public class ResponseMessage {
     private String message;
     public ResponseMessage(String message){
         this.message=message;
     }
     public String getMessage(){
         return message;
     }
     public void setMessage(String message){
         this.message = message;
     }
}
