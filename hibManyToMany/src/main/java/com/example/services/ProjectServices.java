package com.example.services;

import com.example.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ProjectServices {
    void insert(HttpServletRequest req, HttpServletResponse resp);

    List search(HttpServletRequest req, HttpServletResponse resp);

    List searchById(HttpServletRequest request, HttpServletResponse response);

    void update(HttpServletRequest request, HttpServletResponse response);

    List<Object[]> findProjectByEmployee(int id);

    void delete(Project project);
}
