package com.rohitdafda.springbootstudentmanagementsystem.repository;

import com.rohitdafda.springbootstudentmanagementsystem.entities.StudentAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAddressRepository extends JpaRepository<StudentAddress, Integer> {

    List<StudentAddress> findByStudentId(int studentId);
}
