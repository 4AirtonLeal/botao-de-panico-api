package co.ao.BotaoDePanico.security.jwt.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Airton Leal
 */
@Component
public class PasswordEncoder {
    
    //private String password;
    
    public String encodePassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
