package com.rohitdafda.springbootstudentmanagementsystem.services;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.students.CreateStudentRequest;
import com.rohitdafda.springbootstudentmanagementsystem.entities.Student;
import com.rohitdafda.springbootstudentmanagementsystem.handlers.GlobalResponseHandler;
import com.rohitdafda.springbootstudentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(CreateStudentRequest request){
        Student student = new Student();
        student.setStudentCode("0001");
        student.setGender(request.gender());
        student.setName(request.name());
        student.setFatherName(request.fatherName());
        student.setMotherName(request.motherName());
        repository.save(student);

        return student;
    }
}
