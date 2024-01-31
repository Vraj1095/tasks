package com.example.Controller;

import com.example.dtos.StudentRequestDTO;
import com.example.dtos.StudentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class RestTemplateController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/template/student")
    public ResponseEntity<StudentResponseDTO> getStudentList() {
        return restTemplate.getForEntity("http://localhost:8080/student", StudentResponseDTO.class);
    }

    @PostMapping("/template/student")
    public ResponseEntity<StudentResponseDTO> createStudent(@ModelAttribute StudentRequestDTO studentRequestDTO) throws IOException {
        MultiValueMap<String, Object> formData = convertModelToMap(studentRequestDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);
        return restTemplate.postForEntity("http://localhost:8080/student",requestEntity, StudentResponseDTO.class);
    }

    @GetMapping("template/student/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable int id){
        return restTemplate.getForEntity("http://localhost:8080/student/{id}", StudentResponseDTO.class, id);
    }

    @PutMapping(value = "template/student/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@ModelAttribute StudentRequestDTO studentRequestDTO, @PathVariable int id) throws IOException {
        MultiValueMap<String, Object> formData = convertModelToMap(studentRequestDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);
        return restTemplate.exchange("http://localhost:8080/student/{id}",HttpMethod.PUT,requestEntity, StudentResponseDTO.class, id);
    }

    @DeleteMapping("template/student/{id}")
    public ResponseEntity<StudentResponseDTO> deleteStudent(@PathVariable int id){
        return restTemplate.exchange("http://localhost:8080/student/{id}",HttpMethod.DELETE,null, StudentResponseDTO.class, id);
    }

    private MultiValueMap<String, Object> convertModelToMap(@ModelAttribute StudentRequestDTO studentRequestDTO) throws IOException {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("firstname", studentRequestDTO.getFirstname());
        formData.add("lastname", studentRequestDTO.getLastname());
        formData.add("phonenumber", studentRequestDTO.getPhonenumber());
        formData.add("age", studentRequestDTO.getAge());
        formData.add("email", studentRequestDTO.getEmail());
        formData.add("password", studentRequestDTO.getPassword());
        formData.add("file", new FileSystemResource(convertMultipartFileToFile(studentRequestDTO.getFile())));
        return formData;
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }
        return file;
    }
}
