package com.example.service;

import com.example.dao.StudentDAO;
import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class StudentSeriveImp implements StudentService{
    @Autowired
    StudentDAO studentDAO;
    @Override
    public void insertStudent(Student student) {
        this.studentDAO.insertStudent(student);
    }

    @Override
    public List<Student> findAllStudent() {
        return this.studentDAO.searchAllStudent();
    }

    @Override
    public Student findStudentById(int id) {
        Student student = new Student();
        student.setId(id);
        return (Student) this.studentDAO.searchStudent(student).get(0);
    }

    @Override
    public void deleteStudent(Student student) {
        this.studentDAO.deleteStudent(student);
    }
}
