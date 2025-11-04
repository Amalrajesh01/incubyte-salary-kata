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

    public SalaryStats getSalaryStatsByCountry(String country) {
        validateCountry(country);

        List<Employee> employees = employeeRepository.findByCountry(country);
        if (employees.isEmpty()) {
            throw new IllegalArgumentException("No employees found for country: " + country);
        }

        DoubleSummaryStatistics stats = employees.stream()
                .mapToDouble(Employee::getSalary)
                .summaryStatistics();

        return new SalaryStats(stats.getMin(), stats.getMax(), stats.getAverage());
    }

    private void validateCountry(String country) {
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or blank");
        }
    }

    public static record SalaryStats(double min, double max, double avg) {}
}
