package org.sm.finance.utils.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private String name;

    

    // Constructor
    public CustomUserDetails(Long id, String email, String password,String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Getter for ID
    public Long getId() {
        return id;
    }
    public String getName()
    {
        return email;
    }


    @Override
    public String getUsername() {
        return email; // Use email as the username
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // Return roles if needed, null if no roles
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
