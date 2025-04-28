package com.rohitdafda.springbootstudentmanagementsystem.repository;

import com.rohitdafda.springbootstudentmanagementsystem.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Integer> {

    // You can also add custom query methods if needed later.

    // Example if needed in future:
    // List<Courses> findByType(String type);
}
