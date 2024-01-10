package com.example.controller;

import com.example.dao.EmployeeDao;
import com.example.dao.ProjectDao;
import com.example.model.Employee;
import com.example.model.Project;
import com.example.services.EmployeeServices;
import com.example.services.EmployeeServicesImp;
import com.example.services.ProjectServices;
import com.example.services.ProjectServicesImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/projectController")
public class ProjectController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        if (flag.equals("insert")) {
            insert(request, response);
        }
        else if (flag.equals("update")){
            update(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        if(flag.equals("search")){
            search(request,response);
        } else if (flag.equals("edit")) {
            edit(request,response);
        } else if (flag.equals("insert")) {
            insertProject(request, response);
        } else if (flag.equals("delete")) {
            delete(request, response);
        }
    }

    private void insertProject(HttpServletRequest request, HttpServletResponse response) throws IOException {

        EmployeeServices employeeServices = new EmployeeServicesImp();
        List employeeList = employeeServices.search(request, response);
        HttpSession session = request.getSession();
        session.setAttribute("employeeList",employeeList);
        response.sendRedirect("addProject.jsp");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProjectServices projectServices = new ProjectServicesImp();
        Project project = (Project) projectServices.searchById(request, response).get(0);
        projectServices.delete(project);
        response.sendRedirect("projectController?flag=search");
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProjectServices projectService = new ProjectServicesImp();
        EmployeeDao employeeDao = new EmployeeDao();
        List<Project> projectList = projectService.search(request, response);
        for(int i = 0; i < projectList.size();i++){
            Project project = projectList.get(i);
            List<Object[]> list= employeeDao.findEmployeeByProject(project.getProjectId());
            List<Employee> employeeList = new ArrayList<>();
            for(Object[] row: list){
                Employee employee = new Employee();
                employee.setEmployeeId((int)row[0]);
                employee.setEmployeeName((String)row[1]);
                employeeList.add(employee);
            }
            project.setEmployees(employeeList);
            projectList.set(i,project);
        }
        HttpSession session = request.getSession();
        session.setAttribute("projectList",projectList);
        response.sendRedirect("projectData.jsp");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProjectServices projectService = new ProjectServicesImp();
        List projectList = projectService.searchById(request, response);
        HttpSession session = request.getSession();
        EmployeeDao employeeDao = new EmployeeDao();
        List<Object[]> list = employeeDao.findEmployeeByProject(((Project)projectList.get(0)).getProjectId());
        List<Employee> employeeList = new ArrayList<>();
        for(Object[] row: list){
            Employee employee = new Employee();
            employee.setEmployeeId((int)row[0]);
            employee.setEmployeeName((String)row[1]);
            employeeList.add(employee);
        }
        ((Project) projectList.get(0)).setEmployees(employeeList);
        session.setAttribute("projectList",projectList);
        List<Object[]> allEmployeeList = employeeDao.findEmployeeNotOfProject(((Project)projectList.get(0)).getProjectId());
        List<Employee> otheremployeeList = new ArrayList<>();
        for(Object[] row: allEmployeeList){
            Employee employee = new Employee();
            employee.setEmployeeId((int)row[0]);
            employee.setEmployeeName((String)row[1]);
            otheremployeeList.add(employee);
        }
        session.setAttribute("employeeList",otheremployeeList);
        response.sendRedirect("editProject.jsp");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProjectServices projectService = new ProjectServicesImp();
        projectService.update(request,response);
        response.sendRedirect("projectController?flag=search");
    }


    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProjectServices projectService = new ProjectServicesImp();
        projectService.insert(req, resp);
        resp.sendRedirect("index.jsp");
    }
}
