package org.sm.finance.utils.authentication;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class RetrieveDetailsFromAuth {
   
    public CustomUserDetails retrieveUserDetailsFromAuth(){
    Authentication auth  = SecurityContextHolder.getContext().getAuthentication();
    Object principal = auth.getPrincipal();
    CustomUserDetails userDetails = (CustomUserDetails)principal;
    return(userDetails);
    }


    
}
