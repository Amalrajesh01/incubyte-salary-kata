package com.incubyte.service;

import com.incubyte.model.Employee;
import com.incubyte.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        // implement logic in Green phase later
        return null;
    }
}
