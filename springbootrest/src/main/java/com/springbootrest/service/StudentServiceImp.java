package com.springbootrest.service;

import com.springbootrest.model.Student;
import com.springbootrest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImp implements  StudentService{

    @Autowired
    StudentRepository studentRepository;
    @Override
    public Student insertStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudentById(int studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found with Id"+studentId));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }
}
