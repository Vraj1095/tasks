package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {

    private String firstname;

    private String lastname;

    private long phonenumber;

    private int age;

    private String email;

    private String password;

    private MultipartFile file;
}
