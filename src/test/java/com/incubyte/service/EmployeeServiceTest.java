package com.incubyte.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.incubyte.model.Employee;
import com.incubyte.repository.EmployeeRepository;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateEmployeeSuccessfully() {
        Employee emp = new Employee();
        emp.setFullName("Amal Rajesh");
        emp.setJobTitle("Software Engineer");
        emp.setCountry("India");
        emp.setSalary(80000.0);

        Employee savedEmp = new Employee();
        savedEmp.setId(1L);
        savedEmp.setFullName("Amal Rajesh");
        savedEmp.setJobTitle("Software Engineer");
        savedEmp.setCountry("India");
        savedEmp.setSalary(80000.0);

        when(employeeRepository.save(emp)).thenReturn(savedEmp);

        Employee result = employeeService.createEmployee(emp);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Amal Rajesh", result.getFullName());
        verify(employeeRepository, times(1)).save(emp);
    }
}
