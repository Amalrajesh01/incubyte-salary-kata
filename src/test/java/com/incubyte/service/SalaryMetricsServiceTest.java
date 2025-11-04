package com.incubyte.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.incubyte.model.Employee;
import com.incubyte.repository.EmployeeRepository;

class SalaryMetricsServiceTest {

    private final EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    private final SalaryMetricsService salaryMetricsService = new SalaryMetricsService(employeeRepository);

    @Test
    void shouldReturnMinMaxAvgSalaryForCountry() {
        // given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Amal Rajesh", "Engineer", "India", 80000.0),
                new Employee(2L, "John Doe", "Manager", "India", 120000.0),
                new Employee(3L, "Priya", "Designer", "India", 100000.0)
        );

        when(employeeRepository.findByCountry("India")).thenReturn(employees);

        // when
        SalaryMetricsService.SalaryStats stats = salaryMetricsService.getSalaryStatsByCountry("India");

        // then
        assertEquals(80000.0, stats.min());
        assertEquals(120000.0, stats.max());
        assertEquals(100000.0, stats.avg());
        verify(employeeRepository, times(1)).findByCountry("India");
    }
    
    @Test
    void shouldReturnAverageSalaryForJobTitle() {
        // given
        String jobTitle = "Engineer";
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Amal", "Engineer", "India", 80000.0),
                new Employee(2L, "John", "Engineer", "USA", 100000.0),
                new Employee(3L, "Priya", "Engineer", "India", 120000.0)
        );

        when(employeeRepository.findByJobTitle(jobTitle)).thenReturn(employees);

        // when
        double avgSalary = salaryMetricsService.getAverageSalaryByJobTitle(jobTitle);

        // then
        assertEquals(100000.0, avgSalary);
        verify(employeeRepository, times(1)).findByJobTitle(jobTitle);
    }

}
