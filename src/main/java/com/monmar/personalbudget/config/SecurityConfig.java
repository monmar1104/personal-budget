package com.monmar.personalbudget.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //add your custom encoding filter as the first filter in the chain
//        http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);
//
//        http.authorizeRequests()
//                .and()
        // your code here ...
    }
}