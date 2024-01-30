package com.springbootrest.Service;

import com.springbootrest.Exception.StudentNotFoundException;
import com.springbootrest.dto.StudentRequestDTO;
import com.springbootrest.model.Student;
import com.springbootrest.repository.StudentRepository;
import com.springbootrest.service.StudentServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceTest {

    @MockBean
    StudentRepository studentRepositoryMock;

    @Autowired
    StudentServiceImp studentService;

    @Test
    void getStudentForGivenId(){

        int id = 3;
        Student expectedStudent = new Student();

        when(studentRepositoryMock.findById(id)).thenReturn(Optional.of(expectedStudent));

        Student retrivedStudent = studentService.findStudentById(id);

        verify(studentRepositoryMock,times(1)).findById(id);

        assertThat(retrivedStudent).isNotNull();
        assertEquals(retrivedStudent, expectedStudent);
    }

    @Test
    void getStudentForGivenIdNotFound(){

        int id = 999;

        StudentNotFoundException studentNotFoundException = new StudentNotFoundException("Student with id=999 not found");
        when(studentRepositoryMock.findById(id)).thenReturn(Optional.empty());

        Exception actualException = assertThrows(StudentNotFoundException.class, () -> studentService.findStudentById(id));

        verify(studentRepositoryMock, times(1)).findById(id);

        assertEquals(studentNotFoundException.getMessage(),actualException.getMessage());
    }

    @Test
    void shouldCreateStudent(){

        Student student = createStudent();

        StudentRequestDTO studentRequestDTO = createStudentRequestDTO();

        when(studentRepositoryMock.save(any(Student.class))).thenReturn(student);

        Student savedStudent = studentService.insertStudent(studentRequestDTO);

        verify(studentRepositoryMock, times(1)).save(student);
        assertEquals(savedStudent,student);

    }

    @Test
    void updateStudent(){

        int id = 3;
        Student exisitngStudent = new Student();
        exisitngStudent.setAge(30);
        StudentRequestDTO studentRequestDTO = createStudentRequestDTO();
        studentRequestDTO.setAge(30);

        when(studentRepositoryMock.findById(id)).thenReturn(Optional.of(exisitngStudent));
        when(studentRepositoryMock.save(any(Student.class))).thenReturn(exisitngStudent);

        Student expectedStudent =  studentService.updateStudent(studentRequestDTO, 3);

        verify(studentRepositoryMock, times(1)).findById(id);
        verify(studentRepositoryMock, times(1)).save(exisitngStudent);
        assertEquals(expectedStudent,exisitngStudent);
    }

    @Test
    void updateStudentNotFound() {

        int id = 999;
        StudentRequestDTO  studentRequestDTO = createStudentRequestDTO();
        StudentNotFoundException studentNotFoundException = new StudentNotFoundException("Student with id=999 not found");

        when(studentRepositoryMock.findById(id)).thenReturn(Optional.empty());

        Exception actualException = assertThrows(StudentNotFoundException.class, () -> studentService.updateStudent(studentRequestDTO,id));

        verify(studentRepositoryMock, times(1)).findById(id);

        assertEquals(studentNotFoundException.getMessage(),actualException.getMessage());
    }

    @Test
    void getAllStudents(){

        List<Student> expectedStudentList = new ArrayList<>();

        when(studentRepositoryMock.findAll()).thenReturn(expectedStudentList);

        List<Student> actualStudentList = studentService.getAllStudents();

        verify(studentRepositoryMock,times(1)).findAll();
        assertEquals(expectedStudentList,actualStudentList);
    }

    private Student createStudent() {
        Student student = new Student();
        student.setFirstname("Suresh");
        student.setLastname("Mehta");
        student.setAge(35);
        student.setPhonenumber(9856279554L);
        student.setEmail("mehta@gmail.com");
        student.setPassword("Mehta@123");
        student.setFileName("nature.jpeg");
        student.setFileURL("src/main/resources/uploads/nature.jpeg");
        return student;
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
