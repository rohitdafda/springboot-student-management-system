package com.rohitdafda.springbootstudentmanagementsystem.controllers;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.ApiErrorResponse;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.auth.admin.AdminLoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Operation(summary = "Authenticate Admin and return token")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AdminLoginResponse.class)))
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @GetMapping(value = "/adminValue")
    public ResponseEntity<AdminLoginResponse> adminValue() {
        return ResponseEntity.ok(new AdminLoginResponse("heymama", "token"));
    }

}
