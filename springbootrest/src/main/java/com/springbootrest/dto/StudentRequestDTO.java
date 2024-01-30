package com.springbootrest.dto;

import com.springbootrest.constraint.ValidFile;
import com.springbootrest.constraint.ValidPassword;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {

    @NotBlank(message = "First Name: It cannot be empty")
    private String firstname;

    @NotBlank(message = "Last Name: It cannot be empty")
    private String lastname;

    @NotNull(message = "Phone Number: cannot be null")
    @Digits(integer = 10, fraction = 0, message = "Phone Number: must be a numeric value with up to 10 digits")
    private long phonenumber;

    @Min(value = 12, message = "Age: must be greater than 12")
    @Max(value = 40, message = "Age: must be less than 40")
    private int age;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email: is not in proper format")
    private String email;

    @ValidPassword
    private String password;

    @ValidFile
    private MultipartFile file;
}
