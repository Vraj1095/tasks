package com.example.controller;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserServiceDetailsImp;
import com.example.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    UserServiceDetailsImp userServiceDetailsImp;

    @GetMapping("/")
    public String loadHome(){
        return "Hello";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already there";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User Registered SuccessFully";
    }

    @PostMapping("/authenticate")
    public String getAuthenticate(@RequestBody User user) throws  Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        } catch (Exception e){
            throw new Exception("Invalid Credentials", e);
        }
        final UserDetails userDetails = userServiceDetailsImp.loadUserByUsername(user.getUsername());

        return jwtUtils.generateToken(userDetails);
    }
}
