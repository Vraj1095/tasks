package com.springbootrest.Controller;

import com.springbootrest.controller.StudentController;
import com.springbootrest.dto.StudentRequestDTO;
import com.springbootrest.dto.StudentResponseDTO;
import com.springbootrest.model.Student;
import com.springbootrest.service.StudentServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private StudentController studentControllerMock;

    @MockBean
    private StudentServiceImp studentServiceMock;

    @Test
    void getStudentById() {

        //Request
        int id = 3;

        //Mock
        Student student = new Student();
        ResponseEntity<StudentResponseDTO> expectedResponse = new ResponseEntity<>(new StudentResponseDTO("Fetched Student with Id=" + id, student), HttpStatus.OK);

        //Mocking
        when(studentServiceMock.findStudentById(id)).thenReturn(student);

        //Test
        ResponseEntity<StudentResponseDTO> actualResponse = studentControllerMock.getStudentById(id);

        //Assertions
        verify(studentServiceMock, times(1)).findStudentById(id);
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    void createStudent() {

        // Request
        Student student = new Student();
        StudentRequestDTO studentRequestDTO = createStudentRequestDTO();

        //Mock
        ResponseEntity<StudentResponseDTO> expectedResponse = new ResponseEntity<>(new StudentResponseDTO("Student Inserted Successfully..!!!", student), HttpStatus.OK);

        //Mocking
        when(studentServiceMock.insertStudent(studentRequestDTO)).thenReturn(student);

        //Test
        ResponseEntity<StudentResponseDTO> actualResponse = studentControllerMock.createStudent(studentRequestDTO);

        //Assertions
        verify(studentServiceMock, times(1)).insertStudent(studentRequestDTO);
        assertEquals(actualResponse.getStatusCode(), expectedResponse.getStatusCode());
        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    void getAllStudents() {

        //Request
        List<Student> studentList = new ArrayList<>();

        //Mock
        ResponseEntity<StudentResponseDTO> expectedResponse = new ResponseEntity<>(new StudentResponseDTO("All Students Data", studentList), HttpStatus.OK);

        //Mocking
        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        //Test
        ResponseEntity<StudentResponseDTO> actualResponse = studentControllerMock.getAllStudents();

        //Assertions
        assertEquals(actualResponse.getStatusCode(), expectedResponse.getStatusCode());
        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    void deleteStudent() {

        //Request
        int id = 3;

        //Mock
        ResponseEntity<StudentResponseDTO> expectedResponse = new ResponseEntity<>(new StudentResponseDTO("Student with id="+id+" deleted Successfully...!!",null), HttpStatus.OK);

        //Mocking
        doNothing().when(studentServiceMock).deleteStudent(id);

        //Test
        ResponseEntity<StudentResponseDTO> actualResponse = studentControllerMock.deleteStudent(id);

        //Assertions
        assertEquals(actualResponse.getStatusCode(), expectedResponse.getStatusCode());
        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    void updateStudent(){
        // Request
        int id = 3;
        Student student = new Student();
        StudentRequestDTO studentRequestDTO = createStudentRequestDTO();

        //Mock
        ResponseEntity<StudentResponseDTO> expectedResponse = new ResponseEntity<>(new StudentResponseDTO("Student Updated Successfully...!!!", student), HttpStatus.OK);

        //Mocking
        when(studentServiceMock.updateStudent(studentRequestDTO,id)).thenReturn(student);

        //Test
        ResponseEntity<StudentResponseDTO> actualResponse = studentControllerMock.updateStudent(studentRequestDTO, id);

        //Assertions
        verify(studentServiceMock, times(1)).updateStudent(studentRequestDTO, id);
        assertEquals(actualResponse.getStatusCode(), expectedResponse.getStatusCode());
        assertEquals(actualResponse, expectedResponse);
    }
    private StudentRequestDTO createStudentRequestDTO() {
        StudentRequestDTO studentRequestDTO = new StudentRequestDTO();
        studentRequestDTO.setFirstname("Suresh");
        studentRequestDTO.setLastname("Mehta");
        studentRequestDTO.setAge(35);
        studentRequestDTO.setPhonenumber(9856279554L);
        studentRequestDTO.setEmail("mehta@gmail.com");
        studentRequestDTO.setPassword("Mehta@123");
        MultipartFile multipartFile = new MockMultipartFile("nature.jpeg", "nature.jpeg", "image/jpeg", "nature.jpeg".getBytes());
        studentRequestDTO.setFile(multipartFile);
        return studentRequestDTO;
    }
}
