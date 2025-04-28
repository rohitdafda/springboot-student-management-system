package com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.auth.admin;

import io.swagger.v3.oas.annotations.media.Schema;

public record AdminLoginResponse(
        @Schema(description = "email")
        String email,
        @Schema(description = "JWT token")
        String token) {
}


