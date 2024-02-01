package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String loadHome(){
        return "Welcome to Home Page please use /user /admin according to your roles";
    }

    @GetMapping("/user/hello")
    public String loadUser(Authentication authentication){
        System.out.println("authentication.getCredentials() = " + authentication.getCredentials());
        System.out.println("authentication.getDetails() = " + authentication.getDetails());
        System.out.println("authentication.getPrincipal() = " + authentication.getPrincipal());
        System.out.println("authentication.getClass() = " + authentication.getClass());
        return "Welcome User with name="+authentication.getName() + " and Roles="+ authentication.getAuthorities();
    }

    @GetMapping("/admin/hello")
    public String loadAdmin(Authentication authentication){
        return "Welcome User with name="+authentication.getName() + " and Roles="+ authentication.getAuthorities();
    }
}
