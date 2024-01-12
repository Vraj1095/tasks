package com.example.controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/loadEmployeeForm")
    public ModelAndView loadEmployeeForm(){
        return new ModelAndView("addEmployee","employee", new Employee());
    }

    @RequestMapping(value="/saveEmployee",method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute Employee employee){
        employeeService.addEmployee(employee);
        return new ModelAndView("redirect:/searchEmployee");
    }

    @RequestMapping("/searchEmployee")
    public ModelAndView viewAllEmployee(){
        List<Employee> employeeList= employeeService.findAllEmployee();
        return new ModelAndView("employeeData","employeeList",employeeList);
    }

    @RequestMapping(value="/editEmployee")
    public ModelAndView editEmployee(@RequestParam int Id){
        Employee employee = this.employeeService.findEmployeeById(Id);
        return new ModelAndView("editEmployee","employee",employee);
    }

    @RequestMapping(value="/updateEmployee",method = RequestMethod.POST)
    public ModelAndView updateEmployee(@ModelAttribute Employee employee){
        employeeService.updateEmployee(employee);
        return new ModelAndView("redirect:/searchEmployee");
    }

    @RequestMapping(value="/deleteEmployee",method = RequestMethod.GET)
    public ModelAndView delete(@ModelAttribute Employee employee,@RequestParam int Id){
        employee.setId(Id);
        employeeService.deleteEmployee(employee);
        return new ModelAndView("redirect:/searchEmployee");
    }
}
