package com.springbootrest.dto;

import com.springbootrest.model.Student;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

   private String message;
   private Object student;

   @Override
   public int hashCode() {
      return Objects.hash(message, student);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      StudentResponseDTO that = (StudentResponseDTO) obj;
      return Objects.equals(message, that.message) &&
              Objects.equals(student, that.student);
   }
}


