package com.rohitdafda.springbootstudentmanagementsystem.controllers.admin;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.GlobalResponse;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.studentAddress.CreateStudentAddressRequest;
import com.rohitdafda.springbootstudentmanagementsystem.entities.StudentAddress;
import com.rohitdafda.springbootstudentmanagementsystem.handlers.GlobalResponseHandler;
import com.rohitdafda.springbootstudentmanagementsystem.services.StudentAddressService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentAddressController {

    private final StudentAddressService addressService;

    public StudentAddressController(StudentAddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(description = "Create address for User")
    @PostMapping("/create")
    public ResponseEntity<GlobalResponse<Object>> create(@Valid @RequestBody CreateStudentAddressRequest request) {
        StudentAddress address = addressService.create(request);
        return GlobalResponseHandler.created(address, "Address created successfully");
    }

    @Operation(description = "Update address for student")
    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<Object>> update(@PathVariable int id, @RequestBody CreateStudentAddressRequest request) {
        StudentAddress updatedAddress = addressService.update(id, request);
        return GlobalResponseHandler.updated(updatedAddress, "Address updated successfully");
    }

    @Operation(description = "Delete address of student")
    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<Object>> delete(@PathVariable int id) {
        addressService.delete(id);
        return GlobalResponseHandler.deleted("Address deleted successfully");
    }

    @Operation(description = "Get all address of student")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<GlobalResponse<Object>> getByStudentId(@PathVariable int studentId) {
        List<StudentAddress> addresses = addressService.getAddressesByStudentId(studentId);
        return GlobalResponseHandler.ok(addresses, "Addresses fetched for student ID: " + studentId);
    }
}
