package com.spring_jpa.jpademo.dao;

import com.spring_jpa.jpademo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
}
