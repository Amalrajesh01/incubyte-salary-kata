package com.incubyte.service;

import com.incubyte.model.Employee;
import com.incubyte.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.DoubleSummaryStatistics;

@Service
public class SalaryMetricsService {

    private final EmployeeRepository employeeRepository;

    public SalaryMetricsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // ✅ Get salary min, max, and average for a country
    public SalaryStats getSalaryStatsByCountry(String country) {
        validate(country, "Country");

        List<Employee> employees = employeeRepository.findByCountry(country);
        if (employees.isEmpty()) {
            throw new IllegalArgumentException("No employees found for country: " + country);
        }

        DoubleSummaryStatistics stats = employees.stream()
                .mapToDouble(Employee::getSalary)
                .summaryStatistics();

        return new SalaryStats(stats.getMin(), stats.getMax(), stats.getAverage());
    }

    // ✅ Get average salary for a specific job title
    public double getAverageSalaryByJobTitle(String jobTitle) {
        validate(jobTitle, "Job title");

        List<Employee> employees = employeeRepository.findByJobTitle(jobTitle);
        if (employees.isEmpty()) {
            throw new IllegalArgumentException("No employees found for job title: " + jobTitle);
        }

        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    // ✅ Generic validator (DRY principle)
    private void validate(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or blank");
        }
    }

    // ✅ Record for clean return type
    public static record SalaryStats(double min, double max, double avg) {}
}
