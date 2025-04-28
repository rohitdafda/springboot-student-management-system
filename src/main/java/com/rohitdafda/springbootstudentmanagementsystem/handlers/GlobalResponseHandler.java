package com.rohitdafda.springbootstudentmanagementsystem.handlers;

import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalResponseHandler {

    public static <T> ResponseEntity<GlobalResponse<T>> ok(T data, String message) {
        GlobalResponse<T> response = new GlobalResponse<>(data, message, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<GlobalResponse<T>> created(T data, String message) {
        GlobalResponse<T> response = new GlobalResponse<>(data, message, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public static <T> ResponseEntity<GlobalResponse<T>> updated(T data, String message) {
        GlobalResponse<T> response = new GlobalResponse<>(data, message, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<GlobalResponse<Object>> deleted(String message) {
        GlobalResponse<Object> response = new GlobalResponse<>(null, message, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<GlobalResponse<Object>> conflict(String message) {
        GlobalResponse<Object> response = new GlobalResponse<>(null, message, HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    public static ResponseEntity<GlobalResponse<Object>> expectationFailed(String message) {
        GlobalResponse<Object> response = new GlobalResponse<>(null, message, HttpStatus.EXPECTATION_FAILED.value());
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
    }
}
