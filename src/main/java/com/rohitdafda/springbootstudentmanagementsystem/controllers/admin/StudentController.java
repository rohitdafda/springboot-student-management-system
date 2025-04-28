package com.rohitdafda.springbootstudentmanagementsystem.controllers.admin;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.GlobalResponse;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.students.CreateStudentRequest;
import com.rohitdafda.springbootstudentmanagementsystem.entities.Student;
import com.rohitdafda.springbootstudentmanagementsystem.handlers.GlobalResponseHandler;
import com.rohitdafda.springbootstudentmanagementsystem.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @Operation(summary = "Create new Student")
    @PostMapping(value = "/create-student")
    public ResponseEntity<GlobalResponse<Object>> login(@Valid @RequestBody CreateStudentRequest request) {
        try {
            Student student = studentService.createStudent(request);
            return GlobalResponseHandler.created(student, "Student Created");
        } catch (BadCredentialsException e) {
            throw e;
        }
    }

    @Operation(summary = "Get All Students")
    @GetMapping("/all")
    public ResponseEntity<GlobalResponse<Object>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return GlobalResponseHandler.ok(students, "Students fetched successfully");
    }

    @Operation(summary = "Get Student By ID")
    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<Object>> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        return GlobalResponseHandler.ok(student, "Student fetched successfully");
    }

    @Operation(summary = "Update Student")
    @PutMapping("/update/{id}")
    public ResponseEntity<GlobalResponse<Object>> updateStudent(
            @PathVariable int id,
            @Valid @RequestBody CreateStudentRequest request
    ) {
        Student updatedStudent = studentService.updateStudent(id, request);
        return GlobalResponseHandler.updated(updatedStudent, "Student updated successfully");
    }

    @Operation(summary = "Delete Student")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalResponse<Object>> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return GlobalResponseHandler.deleted("Student deleted successfully");
    }

    @Operation(summary = "Search Student by Name")
    @GetMapping("/by-name")
    public ResponseEntity<GlobalResponse<Object>> getStudentsByName(@RequestParam("name") String name) {
        List<Student> students = studentService.getStudentsByName(name);
        return GlobalResponseHandler.ok(students, "Students fetched successfully");
    }

    @Operation(summary = "Fetch students by course id")
    @GetMapping("/by-course")
    public ResponseEntity<GlobalResponse<Object>> getStudentsByCourse(@RequestParam("courseId") int courseId) {
        List<Student> students = studentService.getStudentsByCourseId(courseId);
        return GlobalResponseHandler.ok(students, "Students fetched successfully");
    }

    @Operation(summary = "Search Students by name or courseId or both")
    @GetMapping("/search")
    public ResponseEntity<GlobalResponse<Object>> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer courseId
    ) {
        List<Student> students = studentService.searchStudents(name, courseId);
        return GlobalResponseHandler.ok(students, "Students fetched successfully");
    }
}
