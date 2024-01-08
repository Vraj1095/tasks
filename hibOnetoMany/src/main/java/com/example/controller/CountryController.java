package com.example.controller;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.dao.CountryDAO;
import com.example.dao.StateDAO;
import com.example.model.Country;
import com.example.model.State;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/countryController")
public class CountryController extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if(action.equals("insert")){
            StateDAO stateDAO = new StateDAO();
            List stateList = stateDAO.searchState();
            HttpSession session = request.getSession();
            session.setAttribute("stateList",stateList);
            response.sendRedirect("addCountry.jsp");
        }
        else if(action.equals("search")){
            CountryDAO countryDAO = new CountryDAO();
            List list = countryDAO.searchCountry();
            HttpSession session =  request.getSession();
            session.setAttribute("countryList",list);
            response.sendRedirect("countryData.jsp");
        }
        else if(action.equals("delete")){
            Long id = Long.parseLong(request.getParameter("id"));
            Country country = new Country();
            country.setId(id);
            Set<State> stateSet = new HashSet<>();

            CountryDAO countryDAO = new CountryDAO();
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if(action.equals("insert")){
            String name = request.getParameter("cntname");
            String[] stateList = request.getParameterValues("selectedStates");
            Set<State> stateSet = new HashSet<>();
            StateDAO stateDAO = new StateDAO();
            for (String s : stateList) {
                State state = stateDAO.searchStateById(Integer.parseInt(s));
                stateSet.add(state);
            }
            Country country = new Country(name,stateSet);
            CountryDAO countryDAO = new CountryDAO();
            countryDAO.insertCountry(country);
            response.sendRedirect("index.jsp");
        }
    }

    public void destroy() {
    }
}