package com.rohitdafda.springbootstudentmanagementsystem.services;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.students.UpdateStudentProfileRequest;
import com.rohitdafda.springbootstudentmanagementsystem.entities.Courses;
import com.rohitdafda.springbootstudentmanagementsystem.entities.Student;
import com.rohitdafda.springbootstudentmanagementsystem.entities.StudentAddress;
import com.rohitdafda.springbootstudentmanagementsystem.exceptions.NotFoundException;
import com.rohitdafda.springbootstudentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.studentAddress.CreateStudentAddressRequest;

@Service
public class StudentSelfService {

    private final StudentRepository studentRepository;
    private final StudentAddressService studentAddressService;

    public StudentSelfService(StudentRepository studentRepository, StudentAddressService studentAddressService) {
        this.studentRepository = studentRepository;
        this.studentAddressService = studentAddressService;
    }

    public Object getMe(String studentCode) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        return student;
    }

    // Update profile
    public Student updateProfile(String studentCode, UpdateStudentProfileRequest request) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentCode));

        if (request.getEmail() != null) student.setEmail(request.getEmail());
        if (request.getPhoneNumber() != null) student.setPhoneNumber(request.getPhoneNumber());
        if (request.getMotherName() != null) student.setMotherName(request.getMotherName());
        if (request.getFatherName() != null) student.setFatherName(request.getFatherName());

        return studentRepository.save(student);
    }

    // Search assigned courses
    public List<Courses> searchCourses(String studentCode, String courseName) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        List<Courses> assignedCourses = student.getCourses();

        if (courseName != null && !courseName.isBlank()) {
            assignedCourses = assignedCourses.stream()
                    .filter(course -> course.getName().toLowerCase().contains(courseName.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return assignedCourses;
    }

    // Leave course
    public void leaveCourse(String studentCode, int courseId) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        List<Courses> updatedCourses = student.getCourses().stream()
                .filter(course -> course.getId() != courseId)
                .collect(Collectors.toList());

        student.setCourses(updatedCourses);
        studentRepository.save(student);
    }

    public Object updateAddress(String studentCode, int addressId, CreateStudentAddressRequest address) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        studentAddressService.update(addressId, address);

        return student.getAddresses();
    }
}
