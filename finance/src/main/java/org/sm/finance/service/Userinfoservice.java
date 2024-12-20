package org.sm.finance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sm.finance.model.Userinfo;
import org.sm.finance.repository.Userinforepository;
import org.sm.finance.utils.authentication.CustomUserDetails;
import org.sm.finance.utils.authentication.Jwt;
import org.sm.finance.utils.exceptions.userexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Userinfoservice {
    @Autowired
    private Userinforepository userinforepository;
    @Autowired
    private PasswordEncoder passwordencoder;
    @Autowired
    private Jwt jwt;
    public Userinfo SaveUser(Userinfo userinfo)
    {
        List<Userinfo> existinguser =  userinforepository.findByEmail(userinfo.getEmail());
        
        if(existinguser.size() == 0)
        {

             userinfo.setPassword(passwordencoder.encode(userinfo.getPassword()));
            return userinforepository.save(userinfo);
        }
        else{
            throw new userexception("User with this email exists");
        }

        
    }
    public String LoginUser(String email,String password)
    {
          List<Userinfo> user_in_database = userinforepository.findByEmail(email);
          if(user_in_database.size() == 0)
          {
            throw new userexception("User with this email not found");
            
          }
          else
          {
              Userinfo userobject = user_in_database.get(0);
              String password_of_stored_user = userobject.getPassword();
              boolean login_info_response = passwordencoder.matches(password, password_of_stored_user);
              String jwt_token = jwt.createjwttoken(email);
              
              
              if(login_info_response == true)
              {
                 return(jwt_token);
              }
              else
              {
                throw new userexception("Incorrect password");
              }
          }
    }
    public boolean unregisterUser()
    {
        // For Basic Authentication using username and password
      // How Authentiction object looks like

      // Authentication object:UsernamePasswordAuthenticationToken [
      //   Principal=org.sm.finance.utils.authentication.CustomUserDetails@23f3a6f4, Credentials=[PROTECTED], Authenticated=true, 
      //   Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=27300FA6453E4F0DD498752073C3E2F7], 
      //   Granted Authorities=[]]
      
      // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      // System.out.println("Authentication object:"+authentication);
      // String email = authentication.getName();
      // System.out.println(authentication.getId());
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      // principal contains the authenticated userdetails
      // typically it has fields like getPassword(),getName()
      // so typecasting in CustomUserDetails help us to access other fields as well
Object principal = authentication.getPrincipal();
System.out.println(principal);


    CustomUserDetails userDetails = (CustomUserDetails) principal;
    System.out.println("User ID: " + userDetails.getId());// just for understanding
    System.out.println("Email: " + userDetails.getUsername());

        List<Userinfo>existing_users= userinforepository.findByEmail(userDetails.getUsername());
         if(existing_users.size() == 0)
         {
             throw new userexception("user with this email not found");
         }
         else
         {
            Userinfo user_required = existing_users.get(0);
            userinforepository.delete(user_required);
            return(true);
            
         }
    }
}
