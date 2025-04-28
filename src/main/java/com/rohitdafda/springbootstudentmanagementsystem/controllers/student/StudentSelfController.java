package com.rohitdafda.springbootstudentmanagementsystem.controllers.student;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.GlobalResponse;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.students.UpdateStudentProfileRequest;
import com.rohitdafda.springbootstudentmanagementsystem.entities.Student;
import com.rohitdafda.springbootstudentmanagementsystem.handlers.GlobalResponseHandler;
import com.rohitdafda.springbootstudentmanagementsystem.services.StudentSelfService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentSelfController {

    private final StudentSelfService studentSelfService;

    public StudentSelfController(StudentSelfService studentSelfService) {
        this.studentSelfService = studentSelfService;
    }

    @Operation(description = "Update Profile")
    @PutMapping("/profile/update")
    public ResponseEntity<GlobalResponse<Object>> updateProfile(
            @RequestBody UpdateStudentProfileRequest request,
            @RequestParam int studentId   // Normally you would get this from logged-in token (not param)
    ) {
        Student updatedStudent = studentSelfService.updateProfile(studentId, request);
        return GlobalResponseHandler.updated(updatedStudent, "Profile updated successfully");
    }

    @Operation(description = "Courses assigned to student search")
    @GetMapping("/courses/search")
    public ResponseEntity<GlobalResponse<Object>> searchCourses(
            @RequestParam int studentId,
            @RequestParam(required = false) String name
    ) {
        return GlobalResponseHandler.ok(
                studentSelfService.searchCourses(studentId, name),
                "Courses fetched successfully"
        );
    }

    @Operation(description = "Leave course by student")
    @DeleteMapping("/courses/leave/{courseId}")
    public ResponseEntity<GlobalResponse<Object>> leaveCourse(
            @RequestParam int studentId,
            @PathVariable int courseId
    ) {
        studentSelfService.leaveCourse(studentId, courseId);
        return GlobalResponseHandler.deleted("Course left successfully");
    }
}
