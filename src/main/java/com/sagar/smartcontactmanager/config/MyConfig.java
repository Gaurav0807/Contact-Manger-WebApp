package com.sagar.smartcontactmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService getUserDetailService() {
        return new UserDetailsServiceImpl(); // now spring can create object of UserDetailsServiceImpl()
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // now spring can create object of BCryptPasswordEncoder()
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder((passwordEncoder()));

        return daoAuthenticationProvider;
    }
    //implement override methods of AuthenticationManagerBuilder, now we will configure methods.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // we need to tell the Builder that which type of authentication we are doing i.e of database or in memory.
        auth.authenticationProvider(authenticationProvider()); //this if for database authentication.
    }
    //using HttpSecurity we can tell don't protect all routes.
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
        http.authorizeRequests()
        .antMatchers("/user/**").hasRole("USER") //user roles
        .antMatchers("/**").permitAll().and().formLogin()
        .loginPage("/signin")
        .loginProcessingUrl("/dologin")
        .defaultSuccessUrl("/user/index")
        .and().csrf().disable();  //adding custom login page.
    }

    

}
