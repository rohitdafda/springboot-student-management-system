package com.rohitdafda.springbootstudentmanagementsystem.services;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.students.CreateStudentRequest;
import com.rohitdafda.springbootstudentmanagementsystem.entities.Student;
import com.rohitdafda.springbootstudentmanagementsystem.exceptions.NotFoundException;
import com.rohitdafda.springbootstudentmanagementsystem.handlers.GlobalResponseHandler;
import com.rohitdafda.springbootstudentmanagementsystem.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository, StudentRepository studentRepository) {
        this.repository = repository;
    }

    @Transactional
    public Student createStudent(CreateStudentRequest request) {
        Student lastStudent = repository.findTopByOrderByIdDesc();

        String newStudentCode = generateNewStudentCode(lastStudent);

        Student student = new Student();
        student.setStudentCode(newStudentCode);
        student.setGender(request.gender());
        student.setName(request.name());
        student.setFatherName(request.fatherName());
        student.setMotherName(request.motherName());
        repository.save(student);

        return student;
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // READ ONE
    public Student getStudentById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + id));
    }

    // UPDATE
    @Transactional
    public Student updateStudent(int id, CreateStudentRequest request) {
        Student student = repository.findById(id).orElseThrow(() -> new NotFoundException("Student not found with ID: " + id));

        if (request.name() != null) {
            student.setName(request.name());
        }
        if (request.gender() != null) {
            student.setGender(request.gender());
        }
        if (request.fatherName() != null) {
            student.setFatherName(request.fatherName());
        }
        if (request.motherName() != null) {
            student.setMotherName(request.motherName());
        }
        if (request.dob() != null) {
            student.setDob(request.dob());
        }

        return repository.save(student);
    }

    // DELETE
    @Transactional
    public void deleteStudent(int id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + id));

        repository.delete(student);
    }

    private String generateNewStudentCode(Student lastStudent) {
        if (lastStudent == null || lastStudent.getStudentCode() == null) {
            return "0001";
        }

        try {
            int lastCode = Integer.parseInt(lastStudent.getStudentCode());
            int nextCode = lastCode + 1;
            return String.format("%04d", nextCode);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid student code format: " + lastStudent.getStudentCode());
        }
    }

    public List<Student> getStudentsByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Student> getStudentsByCourseId(int courseId) {
        return repository.findByCoursesId(courseId);
    }

    public List<Student> searchStudents(String name, Integer courseId) {
        List<Student> students = repository.findAll();

        if (name != null && !name.isBlank()) {
            students = students.stream()
                    .filter(student -> student.getName() != null && student.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (courseId != null) {
            students = students.stream()
                    .filter(student -> student.getCourses() != null &&
                            student.getCourses().stream().anyMatch(course -> course.getId() == courseId))
                    .collect(Collectors.toList());
        }

        return students;
    }
}
