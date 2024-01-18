package com.springbootrest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequestDTO {
    @NotBlank(message = "")
    private String firstname;
    @NotBlank(message = "")
    private String lastname;
    @NotNull
    private int standard;
}
