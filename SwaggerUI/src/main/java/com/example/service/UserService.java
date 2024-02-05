package com.example.service;

import com.example.dtos.UserDTO;
import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(UserDTO userDTO);

    User findById(int id);

    void deleteById(int id);

    List<User> findAllUsers();

    User updateUser(UserDTO userDTO, int id);

}
