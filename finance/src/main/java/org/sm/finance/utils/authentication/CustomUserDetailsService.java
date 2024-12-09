package org.sm.finance.utils.authentication;

import java.util.List;

import org.sm.finance.model.Userinfo;
import org.sm.finance.repository.Userinforepository;
import org.sm.finance.utils.exceptions.userexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private Userinforepository userinforepository;
   
    @Override
    public UserDetails loadUserByUsername(String email)


    {
        List<Userinfo>existinguser = userinforepository.findByEmail(email);
        if(existinguser.size() == 0)
        {
            throw new userexception("Email not found");

        }
        else{

        Userinfo userobj =  existinguser.get(0);
        return new CustomUserDetails(
            userobj.getId(),
            userobj.getEmail(),
            userobj.getPassword(),
            userobj.getName()

    );
        }



    }
    
}
