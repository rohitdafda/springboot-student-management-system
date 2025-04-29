package com.rohitdafda.springbootstudentmanagementsystem.services;

import com.rohitdafda.springbootstudentmanagementsystem.entities.Student;
import com.rohitdafda.springbootstudentmanagementsystem.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsServiceImpl implements UserDetailsService {

    private final StudentRepository studentRepository;

    public StudentDetailsServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String studentCode) throws UsernameNotFoundException {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found with code: " + studentCode));

        return org.springframework.security.core.userdetails.User.builder()
                .username(student.getStudentCode())
                .password(student.getDob().toString()) // Treat dob as password
                .roles("STUDENT")
                .build();
    }
}
