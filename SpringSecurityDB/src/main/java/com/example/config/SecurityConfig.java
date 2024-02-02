package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/login", "/register").permitAll();
        http.authorizeRequests().antMatchers("/user/**").hasRole("USER").and().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated()
                .and().formLogin().loginPage("/login").successHandler((request, response, authentication) -> {
                    try {
                        handleSuccess(request,response,authentication);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).permitAll().and().logout().permitAll();
    }

    private void handleSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws Exception{
        for(GrantedAuthority grantedAuthority: authentication.getAuthorities()){
            System.out.println("grantedAuthority.getAuthority() = " + grantedAuthority.getAuthority());
            if(grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
                response.sendRedirect("/admin/home");
            else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                response.sendRedirect("/user/home");
            }
        }
    }
}
