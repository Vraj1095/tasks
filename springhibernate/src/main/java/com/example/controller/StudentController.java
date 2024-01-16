package com.example.controller;

import com.example.model.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
class StudentController{

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/insertStudent")
    public ModelAndView loadStudent(){
        return new ModelAndView("addStudent","student", new Student());
    }

    @RequestMapping(value="/saveStudent",method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute Student student){
        this.studentService.insertStudent(student);
        return new ModelAndView("redirect:/searchStudent");
    }

    @RequestMapping(value = "/searchStudent")
    public ModelAndView viewAllStudent(){
        List<Student> studentList = studentService.findAllStudent();
        return new ModelAndView("searchStudent","studentList",studentList);
    }

    @RequestMapping(value="/editStudent")
    public ModelAndView editStudent(@RequestParam int id){
        Student student = this.studentService.findStudentById(id);
        return new ModelAndView("addStudent","student",student);
    }

    @RequestMapping(value="/updateStudent",method = RequestMethod.POST)
    public ModelAndView updateEmployee(@ModelAttribute Student student){
        this.studentService.insertStudent(student);
        return new ModelAndView("searchStudent");
    }

    @RequestMapping(value="/deleteStudent",method = RequestMethod.GET)
    public ModelAndView deleteStudent(@RequestParam int id){
        Student student = this.studentService.findStudentById(id);
        this.studentService.deleteStudent(student);
        return new ModelAndView("redirect:/searchStudent");
    }
}