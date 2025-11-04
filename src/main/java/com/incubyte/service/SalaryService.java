package com.incubyte.service;

import org.springframework.stereotype.Service;

import com.incubyte.model.Employee;
import com.incubyte.repository.EmployeeRepository;

@Service
public class SalaryService {
	 private final EmployeeRepository employeeRepository = null;
    public SalaryBreakdown calculateSalary(String country, double grossSalary) {
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be blank");
        }

        double deductionRate = getDeductionRate(country);
        double tds = grossSalary * deductionRate;
        double netSalary = grossSalary - tds;

        return new SalaryBreakdown(country, grossSalary, tds, netSalary);
    }
    public double calculateNetSalary(String country, double grossSalary) {
        return calculateSalary(country, grossSalary).net();
    }

    private double getDeductionRate(String country) {
        if (country.equalsIgnoreCase("India")) return 0.10;
        if (country.equalsIgnoreCase("United States") || country.equalsIgnoreCase("USA")) return 0.12;
        return 0.0;
    }
    public SalaryBreakdown calculateSalaryByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + employeeId));

        double gross = employee.getSalary();
        double tds = gross * getDeductionRate(employee.getCountry());
        double net = gross - tds;

        return new SalaryBreakdown(employee.getCountry(), gross, tds, net);
    }

   
    public static record SalaryBreakdown(String country, double gross, double tds, double net) {}
}
