/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author laura
 */
@Configuration
@ConfigurationProperties(prefix="custom")
@Setter
@Getter
public class CustomConfig {
    private String dBUsername;
    private String dBPassword;
    private String sVPrt;
    private String sVIp;
    private String jMail;
    private String jMailPass;
    private String dBName;
    
    
}
