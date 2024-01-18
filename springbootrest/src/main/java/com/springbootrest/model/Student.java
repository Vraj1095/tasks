package com.springbootrest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotBlank(message = "First Name is Mandatory")
    private String firstname;
    @NotBlank(message = "Last Name is Mandatory")
    private String lastname;
    @NotNull
    private int standard;

    public Student(String firstname, String lastname, int standard) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.standard = standard;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id &&
                standard == student.standard &&
                Objects.equals(firstname, student.firstname) &&
                Objects.equals(lastname, student.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, standard);
    }
}
