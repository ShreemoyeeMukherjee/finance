package org.sm.finance.config;

import org.sm.finance.utils.authentication.CustomUserDetailsService;
import org.sm.finance.utils.authentication.JwtFilter;
//import org.sm.finance.utils.authentication.passwordencoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class webSecurityConfig {
 @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtfilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //Due to this configuration , db-console page will be available but we cannot view it
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http.authorizeHttpRequests(request->request
        .requestMatchers("/users/create-user","/users/login-user","/db-console/**","/","/login").permitAll()

        .anyRequest().authenticated())
        //.httpBasic(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(formLogin->formLogin.permitAll())
        .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
        
        .build();
    }
     // For Basic Authentication using username and password
    // Here authentication manager will use userdetails service(implemented by us) to retrieve the user from the database
    // based on the username(here , email) provided in basic Auth header in Postman
    // So from postman request will be sent ,
    // Username(here email and password) will be retrieved automatically by Spring Authentication Manager
    // This shall be pass to userdetailsservice class
    // This class has a method loaduserbyname which fetches user from database  based on the primary key provided
    // we have overridden the loaduserbyname method to fetch user from database using email (which is unique)
    // now this method will fetch the user and send the this details to  customuserdetails class . It creates a new object based on this information which is sent to the and sent it to authentication manager
         // This is required as it will help us to retrieve custom fields we want like name , id , photo etc
    // authentication manager will match the password of the retrieved user from database with the password provided by the incoming 
    // request using password encoder and then will authenticate the user
    // then all the details of the user will be available in the context security holder of Spring
    
     @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

   

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    
        



    
    
}
