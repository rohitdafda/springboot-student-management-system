package com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.students;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CreateStudentRequest(
        @Schema(description = "name", example = "John")
        @NotBlank(message = "Student name cannot be blank")
        String name,

        @Schema(description = "gender", example = "male | female")
        @NotBlank(message = "Student gender cannot be blank")
        String gender,

        @Schema(description = "fatherName", example = "Jack")
        @NotBlank(message = "Father name cannot be blank")
        String fatherName,

        @Schema(description = "motherName", example = "Jack")
        @NotBlank(message = "Mother name cannot be blank")
        String motherName,

        @Schema(description = "dob", example = "2007-01-01")
        @NotBlank(message = "DOB cannot be blank")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dob,

        @Schema(description = "studentCode", example = "S00001")
        @NotBlank(message = "Student Code cannot be blank")
        String studentCode) {
}
