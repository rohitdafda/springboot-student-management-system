package com.rohitdafda.springbootstudentmanagementsystem.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate dob;
    private String gender;
    private String studentCode;
    private String motherName;
    private String fatherName;

    @OneToMany(mappedBy = "student")
    private List<StudentAddress> addresses;

    @ManyToMany
    @JoinTable(
            name = "student_courses",  // Join table name
            joinColumns = @JoinColumn(name = "student_id"),  // Column for student reference
            inverseJoinColumns = @JoinColumn(name = "course_id")  // Column for course reference
    )
    private List<Courses> courses;

    public Student() {
        super();
    }
    public Student(int id) {
        super();
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", motherName='" + motherName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", addresses=" + addresses +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
}
