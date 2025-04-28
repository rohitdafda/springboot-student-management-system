package com.rohitdafda.springbootstudentmanagementsystem.controllers.admin;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.GlobalResponse;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.courses.CreateCourseRequest;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.courses.UpdateCourseRequest;
import com.rohitdafda.springbootstudentmanagementsystem.entities.Courses;
import com.rohitdafda.springbootstudentmanagementsystem.handlers.GlobalResponseHandler;
import com.rohitdafda.springbootstudentmanagementsystem.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/courses", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Create new Course")
    @PostMapping("/create")
    public ResponseEntity<GlobalResponse<Object>> createCourse(@Valid @RequestBody CreateCourseRequest request) {
        Courses course = courseService.createCourse(request);
        return GlobalResponseHandler.created(course, "Course created successfully");
    }

    @Operation(summary = "Get All Courses")
    @GetMapping("/all")
    public ResponseEntity<GlobalResponse<Object>> getAllCourses() {
        List<Courses> courses = courseService.getAllCourses();
        return GlobalResponseHandler.ok(courses, "Courses fetched successfully");
    }

    @Operation(summary = "Get Course By Id")
    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<Object>> getCourseById(@PathVariable int id) {
        Courses course = courseService.getCourseById(id);
        return GlobalResponseHandler.ok(course, "Course fetched successfully");
    }

    @Operation(summary = "Update Course")
    @PutMapping("/update/{id}")
    public ResponseEntity<GlobalResponse<Object>> updateCourse(
            @PathVariable int id,
            @Valid @RequestBody UpdateCourseRequest request
    ) {
        Courses updatedCourse = courseService.updateCourse(id, request);
        return GlobalResponseHandler.updated(updatedCourse, "Course updated successfully");
    }

    @Operation(summary = "Delete Course")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalResponse<Object>> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return GlobalResponseHandler.deleted("Course deleted successfully");
    }
}
