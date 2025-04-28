package com.rohitdafda.springbootstudentmanagementsystem.controllers.admin;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.GlobalResponse;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.auth.admin.AdminLoginRequest;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.auth.admin.AdminLoginResponse;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.students.CreateStudentRequest;
import com.rohitdafda.springbootstudentmanagementsystem.entities.Student;
import com.rohitdafda.springbootstudentmanagementsystem.handlers.GlobalResponseHandler;
import com.rohitdafda.springbootstudentmanagementsystem.helpers.JwtHelper;
import com.rohitdafda.springbootstudentmanagementsystem.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/admin/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping(value = "/create-student")
    public ResponseEntity<GlobalResponse<Object>> login(@Valid @RequestBody CreateStudentRequest request) {
        try {

            Student student = studentService.createStudent(request);

            return GlobalResponseHandler.created(student, "Student Created");
        } catch (BadCredentialsException e) {
            throw e;
        }
    }
}
