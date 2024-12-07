package org.sm.finance.controller;



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
            boolean  login_info_response = userinfoservice.LoginUser(login_info_email, login_info_password);
            if(login_info_response == true)
            {
               return(ResponseEntity.ok("User logged in sucessfully"));
            }
            else
            {
                  return(ResponseEntity.badRequest().body("Login failed"));
            }
            
       }
       catch(userexception e)
       {
            return(ResponseEntity.badRequest().body(e.getMessage()));
       }
}

@DeleteMapping("/unregister-user")
public ResponseEntity<String>unregisterUser(@RequestBody Userinfo  unregister_user_info)

{
      try{
      String unregister_user_info_email  = unregister_user_info.getEmail();
      boolean unregister_user_response =  userinfoservice.unregisterUser(unregister_user_info_email);

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
