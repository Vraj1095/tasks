package com.example.controller;

import com.example.dao.StateDAO;
import com.example.model.State;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/stateController")
public class StateController extends HttpServlet {
    private String message;

    public void init() {
        ;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if(action.equals("search")){
            StateDAO stateDAO = new StateDAO();
            List list = stateDAO.searchState();
            HttpSession session = request.getSession();
            session.setAttribute("stateList",list);
            response.sendRedirect("stateData.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if(action.equals("insert")){
            String name = request.getParameter("name");
            StateDAO stateDAO = new StateDAO();
            State state = new State(name);
            stateDAO.insertState(state);
            response.sendRedirect("index.jsp");
        }
    }

    public void destroy() {
    }
}
