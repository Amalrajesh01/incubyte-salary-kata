package com.incubyte.service;

import org.springframework.stereotype.Service;

import com.incubyte.model.Employee;
import com.incubyte.repository.EmployeeRepository;
import org.springframework.util.StringUtils;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
    	 if (employee == null) {
             throw new IllegalArgumentException("Employee cannot be null");
         }
         return employeeRepository.save(employee);
    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
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
