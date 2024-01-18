package com.springbootrest.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootrest.controller.StudentController;
import com.springbootrest.model.Student;
import com.springbootrest.service.StudentServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentServiceImp studentService;

    @Test
    void  shouldGetStudentDetails() throws Exception {
        Student student = new Student(1,"raj","barot",10);
        when(studentService.findStudentById(1)).thenReturn(student);

        MvcResult mvcResult = mockMvc.perform(get("/student/1")).andDo(print()).andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        Student student1 = new ObjectMapper().readValue(contentAsString, Student.class);
        assertThat(student).isNotNull();
        assertThat(student1).isNotNull();
        assertThat(student1).isEqualTo(student);
    }

    @Test
    void shouldCreateStudent() throws Exception{
        Student student = new Student(6,"suresh","patel",9);
        when(studentService.insertStudent(any(Student.class))).thenReturn(student);

        MvcResult mvcResult = mockMvc.perform(post("/student").content("{\n" +
                "  \"firstname\": \"suresh\",\n" +
                "  \"lastname\": \"patel\",\n" +
                "  \"student\": \"9\"\n" +
                "}").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Student student1 = new ObjectMapper().readValue(contentAsString, Student.class);
        assertThat(student).isNotNull();
        assertThat(student1).isNotNull();
        assertThat(student1).isEqualTo(student);
    }
}
