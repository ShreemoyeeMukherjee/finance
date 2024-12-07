package org.sm.finance.service;

import java.util.List;

import org.sm.finance.model.Userinfo;
import org.sm.finance.repository.Userinforepository;

import org.sm.finance.utils.exceptions.userexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Userinfoservice {
    @Autowired
    private Userinforepository userinforepository;
    @Autowired
    private PasswordEncoder passwordencoder;
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
    public boolean LoginUser(String email,String password)
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
              if(login_info_response == true)
              {
                 return(true);
              }
              else
              {
                throw new userexception("Incorrect password");
              }
          }
    }
    public boolean unregisterUser(String email)
    {
         List<Userinfo>existing_users= userinforepository.findByEmail(email);
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
