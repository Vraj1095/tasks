package com.example.controller;

import java.io.*;
import java.util.List;

import com.example.dao.StudentDAO;
import com.example.model.Student;
import com.example.model.StudentDetail;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/studentController")
public class StudentController extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if(action.equals("search")){
            StudentDAO studentDAO = new StudentDAO();
            List list = studentDAO.searchAllStudent();
            HttpSession session = request.getSession();
            session.setAttribute("studentList",list);
            response.sendRedirect("searchStudent.jsp");
        }
        else if(action.equals("edit")){
            int id = Integer.parseInt(request.getParameter("id"));
            Student student =  new Student();
            student.setId(id);
            StudentDAO studentDAO = new StudentDAO();
            List list = studentDAO.searchStudent(student);
            HttpSession session = request.getSession();
            session.setAttribute("studentList",list);
            response.sendRedirect("editStudent.jsp");
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Student student = new Student();
            student.setId(id);
            StudentDAO studentDAO = new StudentDAO();
            Student student1 = (Student) studentDAO.searchStudent(student).get(0);
            studentDAO.deleteStudent(student1);
            response.sendRedirect("studentController?action=search");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String action = request.getParameter("action");

        if(action.equals("insert")){
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String college = request.getParameter("college");
            int noOfProblemsSolved = Integer.parseInt(request.getParameter("noOfProblemsSolved"));

            Student student = new Student(firstName,lastName,email);
            StudentDetail studentDetail = new StudentDetail(college,noOfProblemsSolved);
            student.setStudentDetail(studentDetail);
            studentDetail.setStudent(student);
            StudentDAO studentDAO = new StudentDAO();
            studentDAO.insertStudent(student);
            response.sendRedirect("index.jsp");
        }
        else if(action.equals("update")){
            int id = Integer.parseInt(request.getParameter("id"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String college = request.getParameter("college");
            int noOfProblemsSolved = Integer.parseInt(request.getParameter("noOfProblemsSolved"));

            StudentDAO studentDAO = new StudentDAO();
            Student student = new Student();
            student.setId(id);
            Student student1 = (Student) studentDAO.searchStudent(student).get(0);
            StudentDetail studentDetail = student1.getStudentDetail();
            studentDetail.setCollege(college);
            studentDetail.setNoOfProblemsSolved(noOfProblemsSolved);
            student1.setFirstName(firstName);
            student1.setLastName(lastName);
            student1.setEmail(email);
            student1.setStudentDetail(studentDetail);
            studentDetail.setStudent(student1);
            studentDAO.updateStudent(student1);
            response.sendRedirect("studentController?action=search");
        }
    }

    public void destroy() {
    }
}