package com.example.controller;

import java.io.*;
import java.util.ArrayList;
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
            StateDAO stateDAO = new StateDAO();
            List list = countryDAO.searchCountry();
            for (int i = 0; i < list.size(); i++) {
                Country country = (Country) list.get(i);
                getStates(stateDAO, country);
                list.set(i,country);
            }
            HttpSession session =  request.getSession();
            session.setAttribute("countryList",list);
            response.sendRedirect("countryData.jsp");
        }
        else if(action.equals("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            CountryDAO countryDAO = new CountryDAO();
            StateDAO stateDAO = new StateDAO();
            Country country = (Country) countryDAO.findCountryById(id).get(0);
            getStates(stateDAO, country);
            countryDAO.deleteCountry(country);
            response.sendRedirect("countryController?action=search");
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            CountryDAO countryDAO = new CountryDAO();
            StateDAO stateDAO = new StateDAO();
            List list = countryDAO.findCountryById(id);
            Country country = (Country) list.get(0);
            getStates(stateDAO, country);
            list.set(0,country);
            List<Object[]> states = stateDAO.findStateOfOtherCountry(country.getId());
            List<State> stateLists = new ArrayList<>();
            for (Object[] row : states) {
                State state = new State();
                state.setId((int) row[0]);
                state.setName((String) row[1]);
                stateLists.add(state);
            }
            HttpSession session = request.getSession();
            session.setAttribute("stateList",stateLists);
            session.setAttribute("countryList",list);
            response.sendRedirect("countryEdit.jsp");
        }
    }

    private void getStates(StateDAO stateDAO, Country country) {
        List<Object[]> objects = stateDAO.findStateByCountry(country.getId());
        Set<State> stateList = new HashSet<>();
        for (Object[] row : objects) {
            State state = new State();
            state.setId((int) row[0]);
            state.setName((String) row[1]);
            stateList.add(state);
        }
        country.setStates(stateList);
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
        } else if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("cntname");
            String[] stateList = request.getParameterValues("selectedStates");
            Set<State> stateSet = new HashSet<>();
            StateDAO stateDAO = new StateDAO();
            for (String s : stateList) {
                State state = stateDAO.searchStateById(Integer.parseInt(s));
                stateSet.add(state);
            }
            CountryDAO countryDAO = new CountryDAO();
            Country country = (Country) countryDAO.findCountryById(id).get(0);
            country.setName(name);
            country.setStates(stateSet);
            countryDAO.insertCountry(country);
            response.sendRedirect("countryController?action=search");
        }
    }

    public void destroy() {
    }
}