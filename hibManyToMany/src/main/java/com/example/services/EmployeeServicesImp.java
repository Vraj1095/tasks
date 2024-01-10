package com.example.services;

import com.example.dao.EmployeeDao;
import com.example.dao.ProjectDao;
import com.example.model.Employee;
import com.example.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class EmployeeServicesImp implements EmployeeServices {
    @Override
    public void insert(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("insertEmp");
        Employee emp = new Employee();
        emp.setEmployeeName(name);
        EmployeeDao dao = new EmployeeDao();
        dao.insert(emp);
    }

    @Override
    public List search(HttpServletRequest request, HttpServletResponse response) {
        EmployeeDao dao = new EmployeeDao();
        List searchEmp = dao.search();
        return searchEmp;
    }
}
