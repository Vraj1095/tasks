package com.spring_jpa.jpademo.service;

import com.spring_jpa.jpademo.model.User;

import java.util.List;
public interface UserService {

    void addUser(User user);
    List<User> findAllUser();
    User findUserById(User user);

    void updateUser(User user, int userId);

    void deleteUser(User user);

}
