package com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.studentAddress;

import jakarta.validation.constraints.NotNull;

public class CreateStudentAddressRequest {

    @NotNull
    private Integer studentId;

    private String street;
    private String area;

    @NotNull
    private String city;

    @NotNull
    private String pincode;

    @NotNull
    private String addressType;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
