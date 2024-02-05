package com.example.controller;

import com.example.dtos.UserDTO;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/api/user")
    public List<User> findAllUser(){
        return this.userService.findAllUsers();
    }

    @PostMapping("/api/user")
    public User saveUser(@RequestBody UserDTO user){
        return this.userService.save(user);
    }

    @GetMapping("/api/user/{id}")
    public User findUserById(@PathVariable int id){
        return this.userService.findById(id);
    }

    @PutMapping("/api/user/{id}")
    public User updateUser(@RequestBody UserDTO userDTO, @PathVariable int id){
        return this.userService.updateUser(userDTO,id);
    }

    @DeleteMapping("/api/user/{id}")
    public String deleteUser(@PathVariable int id){
        this.userService.deleteById(id);
        return "User Deleted SuccessFully";
    }
}
