package com.testref.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Security configuration setup.
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        
        // Inform Spring Security where to get users and their details.
        authenticationManagerBuilder
            .jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, enabled from tr_ss_schema.users where username = ?")
            .authoritiesByUsernameQuery("select username, authority from tr_ss_schema.authorities where username = ?")
            .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        
        // Inform Spring Security how to retrieve user's credentials - we will user basic authentication.
        // NOTE: Used role names shouldn't start with prefix "ROLE_"!
        httpSecurity
            .authorizeRequests()
            .anyRequest()
            .hasAnyRole("ADMIN", "USER")
            .and()
            .httpBasic(); // Use Basic authentication.
    }
}
