package com.sso.backend.configuration;

import com.sso.backend.security.UaaLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UaaLogoutSuccessHandler uaaLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().ignoringAntMatchers("/logout")
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .oauth2Login()
                .and()
                .logout().logoutSuccessHandler(uaaLogoutSuccessHandler);
    }
}

