package com.example.services;

import com.example.dao.EmployeeDao;
import com.example.dao.ProjectDao;
import com.example.model.Employee;
import com.example.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectServicesImp implements ProjectServices {
    @Override
    public void insert(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("projectName");
        String[] employeeIds = req.getParameterValues("employeeId");
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> employeeList = new ArrayList<>();
        for(String employeeId: employeeIds){
            Employee employee = employeeDao.edit(employeeId).get(0);
            employeeList.add(employee);
        }
        Project project = new Project();
        project.setProjectName(name);
        project.setEmployees(employeeList);
        ProjectDao projectDao = new ProjectDao();
        projectDao.insert(project);
    }

    @Override
    public List<Project> search(HttpServletRequest req, HttpServletResponse resp) {
        ProjectDao projectDao = new ProjectDao();
        List<Project> projectList = projectDao.search();
        return projectList;
    }

    @Override
    public List searchById(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        ProjectDao projectDao = new ProjectDao();
        List<Project> projectList = projectDao.findProjectById(id);
        return projectList;
    }

    @Override
    public void update(HttpServletRequest request, HttpServletResponse response) {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        String[] employeeId = request.getParameterValues("employeeId");
        ProjectDao projectDao = new ProjectDao();
        Project project = projectDao.findProjectById(projectId).get(0);
        List<Employee> employeeSet = new ArrayList<>();
        for(String employeeid: employeeId){
            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = employeeDao.edit(employeeid).get(0);
            employeeSet.add(employee);
        }
        project.setEmployees(employeeSet);
        projectDao.update(project);
    }

    @Override
    public List<Object[]> findProjectByEmployee(int id) {
        ProjectDao projectDao = new ProjectDao();
        List<Object[]> list = projectDao.findProjectByEmployee(id);
        return list;
    }

    @Override
    public void delete(Project project) {
        ProjectDao projectDao = new ProjectDao();
        projectDao.delete(project);
    }
}
