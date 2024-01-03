package com.springbootrest.controller;

import com.springbootrest.model.Student;
import com.springbootrest.service.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImp studentServiceImp;

    @GetMapping
    public List<Student> getAllStudents() {
        return  studentServiceImp.getAllStudents();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentServiceImp.insertStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getEmployeeById(@PathVariable int id){
        Student student = studentServiceImp.findStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable int id){
        Student student1  = studentServiceImp.findStudentById(id);
        student1.setFirstname(student.getFirstname());
        student1.setLastname(student.getLastname());
        student1.setStandard(student.getStandard());
        studentServiceImp.insertStudent(student1);

        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable int id){
        Student student = studentServiceImp.findStudentById(id);
        studentServiceImp.deleteStudent(student);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
