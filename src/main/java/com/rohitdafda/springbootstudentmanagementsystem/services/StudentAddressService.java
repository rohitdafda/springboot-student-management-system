package com.rohitdafda.springbootstudentmanagementsystem.services;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.studentAddress.CreateStudentAddressRequest;
import com.rohitdafda.springbootstudentmanagementsystem.entities.Student;
import com.rohitdafda.springbootstudentmanagementsystem.entities.StudentAddress;
import com.rohitdafda.springbootstudentmanagementsystem.exceptions.NotFoundException;
import com.rohitdafda.springbootstudentmanagementsystem.repository.StudentAddressRepository;
import com.rohitdafda.springbootstudentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAddressService {

    private final StudentAddressRepository addressRepository;
    private final StudentRepository studentRepository;

    public StudentAddressService(StudentAddressRepository addressRepository, StudentRepository studentRepository) {
        this.addressRepository = addressRepository;
        this.studentRepository = studentRepository;
    }

    public StudentAddress create(CreateStudentAddressRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + request.getStudentId()));

        StudentAddress address = new StudentAddress();
        address.setStudent(student);
        address.setStreet(request.getStreet());
        address.setArea(request.getArea());
        address.setCity(request.getCity());
        address.setPincode(request.getPincode());
        address.setAddressType(request.getAddressType());

        return addressRepository.save(address);
    }

    public StudentAddress getById(int id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found with id: " + id));
    }

    public StudentAddress update(int id, CreateStudentAddressRequest request) {
        StudentAddress address = getById(id);

        if (request.getStreet() != null) address.setStreet(request.getStreet());
        if (request.getArea() != null) address.setArea(request.getArea());
        if (request.getCity() != null) address.setCity(request.getCity());
        if (request.getPincode() != null) address.setPincode(request.getPincode());
        if (request.getAddressType() != null) address.setAddressType(request.getAddressType());

        return addressRepository.save(address);
    }

    public void delete(int id) {
        StudentAddress address = getById(id);
        addressRepository.delete(address);
    }

    public List<StudentAddress> getAddressesByStudentId(int studentId) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentId));
        return addressRepository.findByStudentId(studentId);
    }
}
