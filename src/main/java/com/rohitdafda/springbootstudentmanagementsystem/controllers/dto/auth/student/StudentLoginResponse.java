package com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.auth.student;

import io.swagger.v3.oas.annotations.media.Schema;

public record StudentLoginResponse(
        @Schema(description = "studentCode")
        String studentCode,

        @Schema(description = "JWT token")
        String token) {
}


