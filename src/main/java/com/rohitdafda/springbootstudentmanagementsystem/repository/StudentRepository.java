package com.rohitdafda.springbootstudentmanagementsystem.repository;

import com.rohitdafda.springbootstudentmanagementsystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}

