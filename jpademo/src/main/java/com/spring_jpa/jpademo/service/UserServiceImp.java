package com.spring_jpa.jpademo.service;

import com.spring_jpa.jpademo.dao.UserDAO;
import com.spring_jpa.jpademo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserDAO userDAO;
    @Override
    public void addUser(User user) {
        userDAO.save(user);
    }

    @Override
    public List<User> findAllUser() {
        return  userDAO.findAll();
    }

    @Override
    public User findUserById(User user) {
        return userDAO.findById(user.getId()).orElseThrow(() -> new RuntimeException("User Not found with Id:"+user.getId()));
    }

    @Override
    public void updateUser(User user, int userId) {
        User user1 = userDAO.findById(userId).orElseThrow(() -> new RuntimeException("User Not found with Id:"+userId));
        user1.setEmail(user.getEmail());
        user1.setMobile(user.getMobile());
        user1.setName(user.getName());
        userDAO.save(user1);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.delete(user);
    }
}
