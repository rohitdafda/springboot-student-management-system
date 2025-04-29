package com.rohitdafda.springbootstudentmanagementsystem.controllers.student;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.GlobalResponse;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.students.UpdateStudentProfileRequest;
import com.rohitdafda.springbootstudentmanagementsystem.entities.Student;
import com.rohitdafda.springbootstudentmanagementsystem.handlers.GlobalResponseHandler;
import com.rohitdafda.springbootstudentmanagementsystem.services.StudentSelfService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.studentAddress.CreateStudentAddressRequest;

@RestController
@RequestMapping("/api/student")
public class StudentSelfController {

    private final StudentSelfService studentSelfService;

    public StudentSelfController(StudentSelfService studentSelfService) {
        this.studentSelfService = studentSelfService;
    }

    @Operation(description = "Courses assigned to student search")
    @GetMapping("/me")
    public ResponseEntity<GlobalResponse<Object>> myProfile(
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentCode = authentication.getName();

        return GlobalResponseHandler.ok(
                studentSelfService.getMe(studentCode),
                "Profile fetched successfully"
        );
    }

    @Operation(description = "Update Profile")
    @PutMapping("/profile/update")
    public ResponseEntity<GlobalResponse<Object>> updateProfile(
            @RequestBody UpdateStudentProfileRequest request
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentCode = authentication.getName();

        Student updatedStudent = studentSelfService.updateProfile(studentCode, request);
        return GlobalResponseHandler.updated(updatedStudent, "Profile updated successfully");
    }

    @Operation(description = "Courses assigned to student search")
    @GetMapping("/courses/search")
    public ResponseEntity<GlobalResponse<Object>> searchCourses(
            @RequestParam(required = false) String name
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentCode = authentication.getName();

        return GlobalResponseHandler.ok(
                studentSelfService.searchCourses(studentCode, name),
                "Courses fetched successfully"
        );
    }

    @Operation(description = "Leave course by student")
    @DeleteMapping("/courses/leave/{courseId}")
    public ResponseEntity<GlobalResponse<Object>> leaveCourse(
            @PathVariable int courseId
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentCode = authentication.getName();

        studentSelfService.leaveCourse(studentCode, courseId);
        return GlobalResponseHandler.deleted("Course left successfully");
    }

    @PutMapping("/profile/address/update/{addressId}")
    public ResponseEntity<GlobalResponse<Object>> updateAddress(@PathVariable int addressId, @RequestBody CreateStudentAddressRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentCode = authentication.getName();

        return GlobalResponseHandler.ok(studentSelfService.updateAddress(studentCode, addressId, request),"Address updated successfully.");
    }
}
