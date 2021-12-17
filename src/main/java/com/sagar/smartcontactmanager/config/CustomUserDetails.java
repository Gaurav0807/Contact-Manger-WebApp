package com.sagar.smartcontactmanager.config;

import java.util.Collection;
import java.util.List;

import com.sagar.smartcontactmanager.entities.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//Here, we are going to implement inbuilt interface of UserDetails.java

public class CustomUserDetails implements UserDetails {

    private User user; //we will require user, because all username and password will get from user only

    
    //1. make parameterized constructor of user, now using this we get username, password
    public CustomUserDetails(User user) {
        super();
        this.user = user;
    }
 
    //2. we will implement all methods from UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //this will return collection, we will specify role for user

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole());
        //adding simpleGrantedAuthority to list
        return List.of(simpleGrantedAuthority); //so all role will come here. i.e normal role and if we have another role for user we can add other role too.
    }

    @Override
    public String getPassword() {
        
        return user.getPassword();
    }

    @Override
    public String getUsername() {
      
        return user.getEmail();
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
