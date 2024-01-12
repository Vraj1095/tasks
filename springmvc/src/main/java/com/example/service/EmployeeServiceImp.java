package com.example.service;

import com.example.dao.EmployeeDAO;
import com.example.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements  EmployeeService{

    @Autowired
    EmployeeDAO employeeDAO;
    @Override
    public void addEmployee(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeDAO.getEmployees();
    }

    @Override
    public Employee findEmployeeById(int id) {
        return employeeDAO.getEmpById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeDAO.delete(employee.getId());
    }
}
