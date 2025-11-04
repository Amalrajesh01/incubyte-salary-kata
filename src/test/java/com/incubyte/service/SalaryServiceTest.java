package com.incubyte.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SalaryServiceTest {

    private final SalaryService salaryService = new SalaryService();

    @Test
    void shouldCalculateNetSalaryForIndia() {
        double result = salaryService.calculateNetSalary("India", 100000.0);
        assertEquals(90000.0, result);
    }

    @Test
    void shouldCalculateNetSalaryForUnitedStates() {
        double result = salaryService.calculateNetSalary("United States", 100000.0);
        assertEquals(88000.0, result);
    }

    @Test
    void shouldCalculateNetSalaryForOtherCountries() {
        double result = salaryService.calculateNetSalary("Germany", 100000.0);
        assertEquals(100000.0, result);
    }
}
