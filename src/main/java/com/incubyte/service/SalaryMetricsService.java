package com.incubyte.service;

import com.incubyte.model.Employee;
import com.incubyte.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryMetricsService {

    private final EmployeeRepository employeeRepository;

    public SalaryMetricsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public SalaryStats getSalaryStatsByCountry(String country) {
        List<Employee> employees = employeeRepository.findByCountry(country);
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("No employees found for country: " + country);
        }

        double min = employees.stream().mapToDouble(Employee::getSalary).min().orElse(0.0);
        double max = employees.stream().mapToDouble(Employee::getSalary).max().orElse(0.0);
        double avg = employees.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);

        return new SalaryStats(min, max, avg);
    }

    public static record SalaryStats(double min, double max, double avg) {}
}
