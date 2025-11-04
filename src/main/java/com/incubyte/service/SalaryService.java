package com.incubyte.service;

import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    public double calculateNetSalary(String country, double grossSalary) {
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be blank");
        }

        double deductionPercentage = 0.0;

        if (country.equalsIgnoreCase("India")) {
            deductionPercentage = 0.10;
        } else if (country.equalsIgnoreCase("United States") || country.equalsIgnoreCase("USA")) {
            deductionPercentage = 0.12;
        }

        double tds = grossSalary * deductionPercentage;
        return grossSalary - tds;
    }
}
