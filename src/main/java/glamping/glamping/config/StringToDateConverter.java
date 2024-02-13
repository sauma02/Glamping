/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glamping.glamping.config;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Admin
 */
public class StringToDateConverter implements Converter<String, Date> {

    
    private final SimpleDateFormat finalDate;

    public StringToDateConverter(String dateFormatPattern) {
        this.finalDate = new SimpleDateFormat(dateFormatPattern);
        this.finalDate.setLenient(false);
    }
    
    @Override
    public Date convert(String source) {
        try {
            return finalDate.parse(source);
        } catch (Exception e) {
         throw new IllegalArgumentException("Formato de fecha no valido", e);
        }
    }
    
}
