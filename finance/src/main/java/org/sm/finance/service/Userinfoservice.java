package org.sm.finance.service;

import java.util.List;

import org.sm.finance.model.Userinfo;
import org.sm.finance.repository.Userinforepository;
import org.sm.utils.userexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Userinfoservice {
    @Autowired
    private Userinforepository userinforepository;
    public Userinfo SaveUser(Userinfo userinfo)
    {
        List<Userinfo> existinguser =  userinforepository.findByEmail(userinfo.getEmail());
        
        if(existinguser.size() == 0)
        {
            return userinforepository.save(userinfo);
        }
        else{
            throw new userexception("User with this email exists");
        }
    
        
    }
    
}
