package com.springbootrest.Service;

import com.springbootrest.Exception.StudentNotFoundException;
import com.springbootrest.model.Student;
import com.springbootrest.repository.StudentRepository;
import com.springbootrest.service.StudentServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImp studentService;

    @Test
    void shouldGetStudentForGivenId(){
        Student student = new Student(1,"raj","barot",10);
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        Student student1 = studentService.findStudentById(1);
        assertThat(student1.getId()).isEqualTo(student.getId());
        assertThat(student1.getFirstname()).isEqualTo(student.getFirstname());
        assertThat(student1.getLastname()).isEqualTo(student.getLastname());
        assertThat(student1.getStandard()).isEqualTo(student.getStandard());
    }

    @Test
    void shouldThrowNotFoundException(){
        StudentNotFoundException studentNotFoundException = new StudentNotFoundException("Student with id=9 Not found");

        StudentNotFoundException notFoundException = assertThrows(StudentNotFoundException.class, () -> {
            studentService.findStudentById(9);
        });

        assertThat(studentNotFoundException.getMessage()).isEqualTo(notFoundException.getMessage());
    }

    @Test
    void shouldCreateStudent(){
        Student student = new Student("vasanth","pandya",5);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student saveStudent = this.studentService.insertStudent(student);

        assertEquals(student.getFirstname(),saveStudent.getFirstname());
        assertEquals(student.getLastname(),saveStudent.getLastname());
        assertEquals(student.getStandard(),saveStudent.getStandard());
    }
}
