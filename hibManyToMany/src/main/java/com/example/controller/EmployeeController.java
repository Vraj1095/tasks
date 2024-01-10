package com.example.controller;


import com.example.services.EmployeeServices;
import com.example.services.EmployeeServicesImp;


import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(value = "/employeeController")
public class EmployeeController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String flag = request.getParameter("flag");
        if (flag.equals("search")) {
            search(request, response);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EmployeeServices employeeService = new EmployeeServicesImp();
        HttpSession session = request.getSession();
        List employeeList = employeeService.search(request,response);
        session.setAttribute("employeeList",employeeList);
        response.sendRedirect("employeeData.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String flag = request.getParameter("flag");
        if(flag.equals("insert")){
            insert(request,response);
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
       EmployeeServices employeeService = new EmployeeServicesImp();
       employeeService.insert(request,response);
       response.sendRedirect("index.jsp");
    }
}