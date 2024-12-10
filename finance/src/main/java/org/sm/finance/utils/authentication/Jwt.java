package org.sm.finance.utils.authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.sm.finance.utils.exceptions.userexception;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class Jwt {
    private static final String  SECRET_KEY_STRING = "abcd!\\7777123#%&defg%#!gjtbjrbehrbebre@#$%%%%";
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
    }
    public String  createjwttoken(String email)
    {
        Map <String , Object> claims = new HashMap<>();
        // if we want to add any custom field we can add here
        // claims.put("id",id(to be received from userinfoservice, retrieved from database))

        return Jwts.builder()
                .subject(email)
                .claims(claims)
                .signWith(getSigningKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*240))
                .compact();
                
                
                
                
    }
    
    public String validateToken(String token)
    {
        // upon verification we will receive the payload in claims variable
        // Claims is an interface in JJWT
        Claims claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
        System.out.println(claims);
        
        Long expirationTime = (Long)claims.get("exp");
        Date maximum_expiration_time = new Date(expirationTime*1000);
        Date current_time = new Date(System.currentTimeMillis());
        if(maximum_expiration_time.compareTo(current_time)>0)
        {
            return(claims.getSubject());
        }
        else
        {
              return( (String)claims.get("sub"));
        }


    }
    
}
