package com.example.controller;

import com.example.dao.CountryDAO;
import com.example.dao.StateDAO;
import com.example.model.Country;
import com.example.model.State;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(value = "/stateController")
public class StateController extends HttpServlet {
    private String message;

    public void init() {
        ;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if(action.equals("insert")){
            CountryDAO countryDAO = new CountryDAO();
            List countryList = countryDAO.searchCountry();
            HttpSession session = request.getSession();
            session.setAttribute("countryList",countryList);
            response.sendRedirect("addState.jsp");
        }
        else if(action.equals("search")){
            List<State> stateList = getStateList();
            HttpSession session = request.getSession();
            session.setAttribute("stateList",stateList);
            response.sendRedirect("stateData.jsp");
        }
        else if(action.equals("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            State state = new State();
            state.setId(id);
            StateDAO stateDAO = new StateDAO();
            stateDAO.deleteState(state);
            response.sendRedirect("stateController?action=search");
        }
        else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int cid = Integer.parseInt(request.getParameter("cid"));
            StateDAO stateDAO = new StateDAO();
            CountryDAO countryDAO = new CountryDAO();
            State state = stateDAO.searchStateById(id);
            Country country = countryDAO.searchCountryById(cid);
            state.setCountry(country);
            List<State> list = new ArrayList<>(){{add(state);}};
            List countryList = countryDAO.searchCountry();
            HttpSession session = request.getSession();
            session.setAttribute("countryList",countryList);
            session.setAttribute("stateList",list);
            response.sendRedirect("stateEdit.jsp");
        }
    }

    private static List<State> getStateList() {
        StateDAO stateDAO = new StateDAO();
        List<Object[]> list = stateDAO.searchState();
        List<State> stateList = new ArrayList<>();
        for (Object[] row : list) {
            State state = new State();
            state.setId((int) row[0]);
            state.setName((String) row[1]);

            Country country = new Country();
            country.setCountry_id((int) row[2]);
            country.setCountryName((String) row[3]);

            state.setCountry(country);

            stateList.add(state);
        }
        return stateList;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if(action.equals("insert")){
            String name = request.getParameter("stateName");
            int countryId = Integer.parseInt(request.getParameter("selectedCountry"));
            CountryDAO countryDAO = new CountryDAO();
            Country country = countryDAO.searchCountryById(countryId);
            State state = new State(name,country);
            StateDAO stateDAO = new StateDAO();
            stateDAO.insertState(state);
            response.sendRedirect("index.jsp");
        } else if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int cid = Integer.parseInt(request.getParameter("selectedCountry"));
            StateDAO stateDAO = new StateDAO();
            State state = stateDAO.searchStateById(id);
            state.setName(request.getParameter("stateName"));
            CountryDAO countryDAO = new CountryDAO();
            Country country = countryDAO.searchCountryById(cid);
            state.setCountry(country);
            stateDAO.insertState(state);
            response.sendRedirect("stateController?action=search");
        }
    }

    public void destroy() {
    }
}
