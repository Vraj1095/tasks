package com.springbootrest.service;

import com.springbootrest.dto.StudentRequestDTO;
import com.springbootrest.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {

    Student insertStudent(StudentRequestDTO studentRequestDTO);
    Student findStudentById(int studentId);
    List<Student> getAllStudents();
    void deleteStudent(int id);
    Student updateStudent(StudentRequestDTO studentRequestDTO, int id);
}
