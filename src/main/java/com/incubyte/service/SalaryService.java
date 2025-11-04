package com.incubyte.service;

import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    public SalaryBreakdown calculateSalary(String country, double grossSalary) {
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be blank");
        }

        double deductionRate = getDeductionRate(country);
        double tds = grossSalary * deductionRate;
        double netSalary = grossSalary - tds;

        return new SalaryBreakdown(country, grossSalary, tds, netSalary);
    }

    private double getDeductionRate(String country) {
        if (country.equalsIgnoreCase("India")) return 0.10;
        if (country.equalsIgnoreCase("United States") || country.equalsIgnoreCase("USA")) return 0.12;
        return 0.0;
    }

    public static record SalaryBreakdown(String country, double gross, double tds, double net) {}
}
