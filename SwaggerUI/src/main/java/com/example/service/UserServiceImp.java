package com.example.service;

import com.example.dtos.UserDTO;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User save(UserDTO userDTO) {
        User user = new User();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        return this.userRepository.save(user);
    }

    @Override
    public User findById(int id) {
        return this.userRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(int id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User updateUser(UserDTO userDTO, int id) {
        User user = this.userRepository.findById(id).orElseThrow();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        return this.userRepository.save(user);
    }
}
