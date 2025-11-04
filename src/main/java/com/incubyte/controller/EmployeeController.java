package com.incubyte.controller;

import com.incubyte.model.Employee;
import com.incubyte.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // ✅ Constructor Injection (best practice)
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // ✅ Create Employee
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.createEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Get Employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

