package com.example.controller;

import com.example.dao.StateDAO;
import com.example.dao.CountryDAO;
import com.example.model.Country;
import com.example.model.State;

import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "helloServlet", value = "/countryController")
public class CountryController extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if(action.equals("search")){
            CountryDAO countryDAO = new CountryDAO();
            List list = countryDAO.searchCountry();
            HttpSession session =  request.getSession();
            session.setAttribute("countryList",list);
            response.sendRedirect("countryData.jsp");
        }
        else if(action.equals("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            CountryDAO countryDAO = new CountryDAO();
            StateDAO stateDAO = new StateDAO();
            List<Object[]> list = stateDAO.findStateByCountry(id);
            for (Object[] row : list) {
                State state = new State();
                state.setId((int) row[0]);
                state.setName((String) row[1]);
                Country country = new Country();
                country.setCountry_id((int) row[2]);
                country.setCountryName((String) row[3]);
                state.setCountry(country);
                stateDAO.deleteState(state);
            }
            Country country = new Country();
            country.setCountry_id(id);
            countryDAO.deleteCountry(country);
            response.sendRedirect("countryController?action=search");
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            CountryDAO countryDAO = new CountryDAO();
            Country country = countryDAO.searchCountryById(id);
            HttpSession session = request.getSession();
            List<Country> countries = new ArrayList<>(){{add(country);}};
            session.setAttribute("countryData", countries);
            response.sendRedirect("countryEdit.jsp");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if(action.equals("insert")){
            String name = request.getParameter("name");
            Country country = new Country(name);
            CountryDAO countryDAO = new CountryDAO();
            countryDAO.insertCountry(country);
            response.sendRedirect("index.jsp");
        } else if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("countryName");
            CountryDAO countryDAO = new CountryDAO();
            Country country = countryDAO.searchCountryById(id);
            country.setCountryName(name);
            countryDAO.insertCountry(country);
            response.sendRedirect("countryController?action=search");
        }
    }

    public void destroy() {
    }
}