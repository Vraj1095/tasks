package com.hibernate.hbdemo.controller;

import com.hibernate.hbdemo.dao.UserDAO;
import com.hibernate.hbdemo.vo.UserVO;

import java.io.*;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserServlet", value = "/userController")
public class UserController extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");

        if(action.equals("searchUser"))
        {
            UserDAO userDAO = new UserDAO();
            List ls = userDAO.search();

            HttpSession session =  request.getSession();

            session.setAttribute("userData", ls);

            response.sendRedirect("UserData.jsp");
        }
        else if(action.equals("editUser"))
        {
            int id = Integer.parseInt(request.getParameter("id"));

            UserDAO userDAO = new UserDAO();

            UserVO userVO = new UserVO();

            userVO.setId(id);

            List ls = userDAO.edit(userVO);

            HttpSession session = request.getSession();

            session.setAttribute("editData",ls);

            response.sendRedirect("UserEdit.jsp");
        }
        else if(action.equals("deleteUser"))
        {
            int id = Integer.parseInt(request.getParameter("id"));

            UserDAO regDAO = new UserDAO();

            UserVO userVO = new UserVO();

            userVO.setId(id);

            regDAO.delete(userVO);

            response.sendRedirect("userController?action=searchUser");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");

        if(action.equals("saveUser"))
        {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            UserVO userVO = new UserVO();
            userVO.setFirstName(firstName);
            userVO.setLastName(lastName);

            UserDAO userDAO = new UserDAO();
            userDAO.insert(userVO);

            response.sendRedirect("user.jsp");
        }
        if(action.equals("updateUser"))
        {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int id = Integer.parseInt(request.getParameter("id"));

            UserVO userVO = new UserVO();
            userVO.setFirstName(firstName);
            userVO.setLastName(lastName);
            userVO.setId(id);

            UserDAO userDAO = new UserDAO();

            userDAO.update(userVO);

            response.sendRedirect("userController?action=searchUser");
        }
    }

    public void destroy() {
    }
}