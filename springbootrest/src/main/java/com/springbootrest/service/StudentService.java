package com.springbootrest.service;

import com.springbootrest.model.Student;

import java.util.List;

public interface StudentService {

    Student insertStudent(Student student);
    Student findStudentById(int studentId);
    List<Student> getAllStudents();

    void deleteStudent(Student student);
}
