package com.springbootrest.Repository;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentRepositoryTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImp studentService;

    @Test
    void ShouldInsertStudent(){
        Student student = new Student("swapnil","shah",8);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student saveStudent = this.studentService.insertStudent(student);

        assertThat(saveStudent).isNotNull();
        assertThat(saveStudent.getFirstname()).isEqualTo(student.getFirstname());
        assertThat(saveStudent.getLastname()).isEqualTo(student.getLastname());
        assertThat(saveStudent.getStandard()).isEqualTo(student.getStandard());
    }

    @Test
    void ShouldFindStudentById(){
        Student student = new Student("raj","barot",10);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        Student retrivedStudent = studentService.findStudentById(1);

        assertThat(retrivedStudent).isNotNull();
        assertThat(retrivedStudent.getFirstname()).isEqualTo(student.getFirstname());
        assertThat(retrivedStudent.getLastname()).isEqualTo(student.getLastname());
        assertThat(retrivedStudent.getStandard()).isEqualTo(student.getStandard());

        verify(studentRepository,times(1)).findById(1);
    }

    @Test
    void ShouldThrowStudentNotFoundException(){

        when(studentRepository.findById(-1)).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, () -> studentService.findStudentById(-1));
        verify(studentRepository, times(1)).findById(-1);
    }
}
