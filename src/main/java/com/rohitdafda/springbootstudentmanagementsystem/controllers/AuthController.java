package com.rohitdafda.springbootstudentmanagementsystem.controllers;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.auth.admin.AdminLoginRequest;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.auth.admin.AdminLoginResponse;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.ApiErrorResponse;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.auth.student.StudentLoginRequest;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.auth.student.StudentLoginResponse;
import com.rohitdafda.springbootstudentmanagementsystem.helpers.JwtHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(@Qualifier("adminAuthenticationManager") AuthenticationManager adminAuthenticationManager) {
        this.authenticationManager = adminAuthenticationManager;
    }

    @Operation(summary = "Authenticate Admin and return token")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AdminLoginResponse.class)))
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @PostMapping(value = "/admin-login")
    public ResponseEntity<AdminLoginResponse> login(@Valid @RequestBody AdminLoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        } catch (BadCredentialsException e) {
            throw e;
        }

        String token = JwtHelper.generateToken(request.email(),"admin");
        return ResponseEntity.ok(new AdminLoginResponse(request.email(), token));
    }

    @Operation(summary = "Authenticate Student and return token")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AdminLoginResponse.class)))
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @PostMapping(value = "/student-login")
    public ResponseEntity<StudentLoginResponse> login(@Valid @RequestBody StudentLoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.dob(), request.studentCode()));
        } catch (BadCredentialsException e) {
            throw e;
        }

        String token = JwtHelper.generateToken(request.studentCode(),"student");
        return ResponseEntity.ok(new StudentLoginResponse(request.studentCode(), token));
    }
}
