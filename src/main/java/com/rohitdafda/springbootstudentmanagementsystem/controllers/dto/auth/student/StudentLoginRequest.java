package com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.auth.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record StudentLoginRequest(
        @Schema(description = "dob", example = "2007-01-01")
        @NotBlank(message = "DOB cannot be blank")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dob,

        @Schema(description = "studentCode", example = "S00001")
        @NotBlank(message = "Student Code cannot be blank")
        String studentCode) {
}

