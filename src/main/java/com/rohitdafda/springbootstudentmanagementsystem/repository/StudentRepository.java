package com.rohitdafda.springbootstudentmanagementsystem.repository;

import com.rohitdafda.springbootstudentmanagementsystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findTopByOrderByIdDesc();

    List<Student> findAllByOrderByIdDesc();

    Optional<Student> findById(int id);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByCoursesId(int courseId);

}

