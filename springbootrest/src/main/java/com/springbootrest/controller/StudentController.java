package com.springbootrest.controller;

import com.springbootrest.model.Student;
import com.springbootrest.service.StudentService;
import com.springbootrest.service.StudentServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return  studentService.getAllStudents();
    }

    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student){

        return studentService.insertStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getEmployeeById(@PathVariable int id){
        Student student = studentService.findStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable int id){
        Student student1  = studentService.findStudentById(id);
        student1.setFirstname(student.getFirstname());
        student1.setLastname(student.getLastname());
        student1.setStandard(student.getStandard());
        studentService.insertStudent(student1);

        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable int id){
        Student student = studentService.findStudentById(id);
        studentService.deleteStudent(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
