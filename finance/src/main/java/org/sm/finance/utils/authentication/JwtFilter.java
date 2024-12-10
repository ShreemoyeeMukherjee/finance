package org.sm.finance.utils.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtFilter  extends OncePerRequestFilter{
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private Jwt jwt_obj;

    @Override
    // here we are extracting the header named 'Authorization' and then we are checking if it is bearer token
    // after extracting token , it is being validated using a function validateToken()
    // if token is  valid , we will receive the username(ie email here)and then using that we can
    //retrieve the the user using loadByUsername()

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt_token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt_token = authorizationHeader.substring(7);
            username = jwt_obj.validateToken(jwt_token);
        }
        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
            // here userdetails are placed in the Security Context Holder
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
            
        }
        chain.doFilter(request, response);
    }
}
