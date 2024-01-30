package com.springbootrest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;

    private String lastname;

    private long phonenumber;

    private int age;

    private String email;

    private String password;

    private String fileName;

    private String fileURL;

    public Student(String firstname, String lastname, long phonenumber, int age, String email, String password, String fileName, String fileURL) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.age = age;
        this.email = email;
        this.password = password;
        this.fileName = fileName;
        this.fileURL = fileURL;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id &&
                phonenumber == student.phonenumber &&
                age == student.age &&
                Objects.equals(firstname, student.firstname) &&
                Objects.equals(lastname, student.lastname) &&
                Objects.equals(email, student.email) &&
                Objects.equals(password, student.password) &&
                Objects.equals(fileName, student.fileName) &&
                Objects.equals(fileURL, student.fileURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, phonenumber, age, email, password, fileName, fileURL);
    }
}
