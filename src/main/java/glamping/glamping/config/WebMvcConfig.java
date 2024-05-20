/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.config;

import jakarta.mail.Session;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 *
 * @author Admin
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter("dd/mm/yy"));
    }

    @Bean
    public StringToDateConverter stringToDateConverter() {
        return new StringToDateConverter("dd/mm/yy");
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
          JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.hostinger.com");
        mailSender.setPort(465);

        // Replace with your Hostinger email and password
        mailSender.setUsername("mantrasoporte1@xn--mantracabaas-jhb.site");
        mailSender.setPassword("Quinteroarias.1");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", "smtp.hostinger.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.from", "mantrasoporte1@xn--mantracabaas-jhb.site");
        
         Session session = Session.getInstance(props);

        // Set the session for the mail sender
        mailSender.setSession(session);
        
        return mailSender;
    }

    @Bean
    public ClassLoaderTemplateResolver secondaryTemplateResolver() {
        ClassLoaderTemplateResolver secondaryTemplateResolver = new ClassLoaderTemplateResolver();
        secondaryTemplateResolver.setPrefix("css/");
        secondaryTemplateResolver.setSuffix(".css");
        secondaryTemplateResolver.setTemplateMode(TemplateMode.CSS);
        secondaryTemplateResolver.setCharacterEncoding("UTF-8");
        secondaryTemplateResolver.setOrder(1);
        secondaryTemplateResolver.setCheckExistence(true);

        return secondaryTemplateResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");

    }

}
