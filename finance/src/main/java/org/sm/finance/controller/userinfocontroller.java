package org.sm.finance.controller;



import java.util.HashMap;
import java.util.Map;

import org.sm.finance.model.Userinfo;
import org.sm.finance.service.Userinfoservice;
import org.sm.finance.utils.exceptions.userexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

public class userinfocontroller {
    @Autowired
    private Userinfoservice userinfoservice;
    @PostMapping("/create-user")
    public ResponseEntity<Object> createUser(@RequestBody Userinfo userinfo)
    {
         try{
            Userinfo   savedUser =  userinfoservice.SaveUser(userinfo);
            return(ResponseEntity.ok(savedUser));
         }
         catch(userexception e)
         {
               return(ResponseEntity.badRequest().body(e.getMessage()));
         }
    
}
@PostMapping("/login-user")


public ResponseEntity<String> loginUser( @RequestBody Userinfo login_info )
{
       try{
            String login_info_email = login_info.getEmail();      
            String login_info_password = login_info.getPassword();
            String token = userinfoservice.LoginUser(login_info_email, login_info_password);
            if(token !=  null)
            {
               return(ResponseEntity.ok().body(token));
            }
            else
            {
                  return (ResponseEntity.ok().body("Login failed"));
            }
            
            
       }
       catch(userexception e)
       {
           

            return(ResponseEntity.badRequest().body(e.getMessage()));
       }
}

@DeleteMapping("/unregister-user")
public ResponseEntity<String>unregisterUser()

{
      try{
     
      boolean unregister_user_response =  userinfoservice.unregisterUser();

      if(unregister_user_response == true)
      {
      return(ResponseEntity.ok("User unregistered successfully"));
      }
      else
      {
            return(ResponseEntity.badRequest().body("Unregistration failed"));
      }
}
catch(userexception e)
{
      return(ResponseEntity.badRequest().body("User unregistration failed"));
}

}
}
