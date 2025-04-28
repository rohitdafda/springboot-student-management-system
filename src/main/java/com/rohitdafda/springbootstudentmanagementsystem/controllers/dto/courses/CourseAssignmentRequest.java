package com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.courses;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CourseAssignmentRequest {

    @NotNull
    private Integer studentId;

    @NotNull
    private List<Integer> courseIds;

    public CourseAssignmentRequest() {}

    public CourseAssignmentRequest(Integer studentId, List<Integer> courseIds) {
        this.studentId = studentId;
        this.courseIds = courseIds;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public List<Integer> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Integer> courseIds) {
        this.courseIds = courseIds;
    }
}
