package com.rohitdafda.springbootstudentmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
public class StudentAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private String street;
    private String area;

    @NotNull
    private String city;

    @NotNull
    private String pincode;

    @NotNull
    private String addressType; // "permanent", "correspondence", "current"


    public StudentAddress() {
        super();
    }

    public StudentAddress(int id, Student student) {
        super();
        this.id = id;
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentAddress{" +
                "id=" + id +
                ", student=" + student +
                ", street='" + street + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", addressType='" + addressType + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
}
