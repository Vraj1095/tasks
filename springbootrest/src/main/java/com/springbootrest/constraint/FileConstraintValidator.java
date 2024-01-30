package com.springbootrest.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileConstraintValidator implements ConstraintValidator<ValidFile, MultipartFile> {
    @Override
    public void initialize(ValidFile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {

        String contentType = multipartFile.getContentType();
        System.out.println("contentType.toString() = " + contentType.toString());
        if(isSupportedFileContentType(contentType)){
            return true;
        }
        String messageTemplate = "Only PNG, JPG and JPEG files are allowed";
        constraintValidatorContext.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

    private boolean isSupportedFileContentType(String contentType){
        return contentType.equals("image/png") || contentType.equals("image/jpeg") || contentType.equals("image/jpg");
    }
}
