package com.example.service;

import com.example.model.Student;

import java.util.List;

public interface StudentService {

    void insertStudent(Student student);

    List<Student> findAllStudent();

    Student findStudentById(int id);

    void deleteStudent(Student student);
}
