package com.kolomeytsev.testSPA.config;


import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.security.oauth2.client.*;
import org.springframework.boot.autoconfigure.security.oauth2.resource.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.kolomeytsev.testSPA.domain.User;
import com.kolomeytsev.testSPA.repositorys.IUserDetailRepository;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }
    @Bean
    public PrincipalExtractor principalExtractor(IUserDetailRepository userDetailsRepo) {
        return map -> {
               return new User();
            }
      ;
    }
   }
