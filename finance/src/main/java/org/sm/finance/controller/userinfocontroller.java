package org.sm.finance.controller;

import java.util.List;

import org.sm.finance.model.Userinfo;
import org.sm.finance.service.Userinfoservice;
import org.sm.utils.userexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
