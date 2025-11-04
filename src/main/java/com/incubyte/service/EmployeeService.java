package com.incubyte.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.incubyte.model.Employee;
import com.incubyte.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /** CREATE **/
    public Employee createEmployee(Employee employee) {
        validateEmployee(employee);
        return employeeRepository.save(employee);
    }

    /** READ **/
    public Employee getEmployeeById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid employee ID");
        }
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
    }
    
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new IllegalStateException("No employees found");
        }
        return employees;
    }

    public Employee updateEmployee(Long id, Employee updated) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid employee ID");
        }
        // For PUT, we require a complete, valid representation.
        validateEmployee(updated);

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        applyUpdates(existing, updated);
        return employeeRepository.save(existing);
    }

    private void applyUpdates(Employee target, Employee source) {
        target.setFullName(source.getFullName());
        target.setJobTitle(source.getJobTitle());
        target.setCountry(source.getCountry());
        target.setSalary(source.getSalary());
    }
    public void deleteEmployee(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Employee ID must be positive");
        }

        employeeRepository.findById(id)
                .ifPresentOrElse(
                    employeeRepository::delete,
                    () -> { throw new IllegalArgumentException("No employee found with ID: " + id); }
                );
    }



    private void validateEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (!StringUtils.hasText(employee.getFullName())) {
            throw new IllegalArgumentException("Full name is required");
        }

        if (!StringUtils.hasText(employee.getJobTitle())) {
            throw new IllegalArgumentException("Job title is required");
        }

        if (!StringUtils.hasText(employee.getCountry())) {
            throw new IllegalArgumentException("Country is required");
        }

        if (employee.getSalary() == null || employee.getSalary() <= 0) {
            throw new IllegalArgumentException("Salary must be greater than 0");
        }
    }
}
