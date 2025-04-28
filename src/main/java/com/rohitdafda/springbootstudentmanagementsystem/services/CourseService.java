package com.rohitdafda.springbootstudentmanagementsystem.services;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.courses.CreateCourseRequest;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.courses.UpdateCourseRequest;
import com.rohitdafda.springbootstudentmanagementsystem.entities.Courses;
import com.rohitdafda.springbootstudentmanagementsystem.exceptions.NotFoundException;
import com.rohitdafda.springbootstudentmanagementsystem.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Courses createCourse(CreateCourseRequest request) {
        Courses course = new Courses();
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setType(request.getType());
        course.setDuration(request.getDuration());
        course.setTopics(request.getTopics());
        repository.save(course);
        return course;
    }

    public List<Courses> getAllCourses() {
        return repository.findAll();
    }

    public Courses getCourseById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found with ID: " + id));
    }

    public Courses updateCourse(int id, UpdateCourseRequest request) {
        Courses course = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found with ID: " + id));

        if (request.getName() != null) course.setName(request.getName());
        if (request.getDescription() != null) course.setDescription(request.getDescription());
        if (request.getType() != null) course.setType(request.getType());
        if (request.getDuration() != null) course.setDuration(request.getDuration());
        if (request.getTopics() != null) course.setTopics(request.getTopics());

        repository.save(course);
        return course;
    }

    public void deleteCourse(int id) {
        Courses course = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found with ID: " + id));
        repository.delete(course);
    }
}
