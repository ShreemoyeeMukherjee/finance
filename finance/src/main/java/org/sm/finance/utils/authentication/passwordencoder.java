package org.sm.finance.utils.authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class passwordencoder {


    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
        
    }
    
}
