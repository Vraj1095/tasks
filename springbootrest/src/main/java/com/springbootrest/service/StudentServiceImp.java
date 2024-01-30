package com.springbootrest.service;

import com.springbootrest.Exception.StudentNotFoundException;
import com.springbootrest.dto.StudentRequestDTO;
import com.springbootrest.model.Student;
import com.springbootrest.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class StudentServiceImp implements  StudentService{
    @Value("${upload.folder}")
    private String uploadFolder;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student insertStudent(StudentRequestDTO studentRequestDTO){
        createUploadFolderIfNotExists();
        String fileName = studentRequestDTO.getFile().getOriginalFilename();
        String filePath = uploadFolder + File.separator + fileName;
        Student student = new Student();
        student.setAge(studentRequestDTO.getAge());
        student.setFirstname(studentRequestDTO.getFirstname());
        student.setLastname(studentRequestDTO.getLastname());
        student.setEmail(studentRequestDTO.getEmail());
        student.setPassword(studentRequestDTO.getPassword());
        student.setPhonenumber(studentRequestDTO.getPhonenumber());
        try {
            Files.copy(studentRequestDTO.getFile().getInputStream(), Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
        student.setFileName(fileName);
        student.setFileURL(filePath);
        return studentRepository.save(student);
    }

    @Override
    public Student findStudentById(int studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id="+studentId+" not found"));
    }
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(StudentRequestDTO studentRequestDTO, int id) {
        Student student = this.studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id="+id+" not found"));
        student.setAge(studentRequestDTO.getAge());
        student.setFirstname(studentRequestDTO.getFirstname());
        student.setLastname(studentRequestDTO.getLastname());
        student.setEmail(studentRequestDTO.getEmail());
        student.setPassword(studentRequestDTO.getPassword());
        student.setPhonenumber(studentRequestDTO.getPhonenumber());
        String fileName = studentRequestDTO.getFile().getOriginalFilename();
        String filePath = uploadFolder + File.separator + fileName;
        File exisitngFile = new File(filePath);
        if(exisitngFile.exists()){
            student.setFileName(fileName);
            student.setFileURL(filePath);
            return studentRepository.save(student);
        }
        try {
            Files.copy(studentRequestDTO.getFile().getInputStream(), Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
        student.setFileName(fileName);
        student.setFileURL(filePath);
        return this.studentRepository.save(student);
    }

    public void createUploadFolderIfNotExists() {
        File folder = new File(uploadFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
