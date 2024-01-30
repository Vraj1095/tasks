package com.springbootrest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootrest.constraint.ValidFile;
import com.springbootrest.dto.StudentRequestDTO;
import com.springbootrest.dto.StudentResponseDTO;
import com.springbootrest.model.Student;
import com.springbootrest.service.StudentService;
import jakarta.validation.Valid;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.xml.XmlEventDecoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<StudentResponseDTO> getAllStudents() {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setMessage("All Students Data");
        studentResponseDTO.setStudent(studentService.getAllStudents());
        return new ResponseEntity<>(studentResponseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @ModelAttribute StudentRequestDTO studentRequestDTO){
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setStudent(studentService.insertStudent(studentRequestDTO));
        studentResponseDTO.setMessage("Student Inserted Successfully..!!!");
        return new ResponseEntity<>(studentResponseDTO,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable int id){
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setMessage("Fetched Student with Id="+id);
        studentResponseDTO.setStudent(studentService.findStudentById(id));
        return new ResponseEntity<>(studentResponseDTO,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@Valid @ModelAttribute StudentRequestDTO studentRequestDTO,@PathVariable int id){
        Student student = this.studentService.updateStudent(studentRequestDTO, id);
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setMessage("Student Updated Successfully...!!!");
        studentResponseDTO.setStudent(student);
        return new ResponseEntity<>(studentResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StudentResponseDTO> deleteStudent(@PathVariable int id){
        this.studentService.deleteStudent(id);
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setMessage("Student with id="+id+" deleted Successfully...!!");
        studentResponseDTO.setStudent(null);
        return new ResponseEntity<>(studentResponseDTO,HttpStatus.OK);
    }
}
