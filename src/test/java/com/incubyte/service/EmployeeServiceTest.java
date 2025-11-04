package com.incubyte.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    
    @Test
    void shouldReturnEmployeeWhenIdIsValid() {
        // given
        Long employeeId = 1L;
        Employee savedEmp = new Employee();
        savedEmp.setId(employeeId);
        savedEmp.setFullName("Amal Rajesh");
        savedEmp.setJobTitle("Software Engineer");
        savedEmp.setCountry("India");
        savedEmp.setSalary(80000.0);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(savedEmp));

        // when
        Employee result = employeeService.getEmployeeById(employeeId);

        // then
        assertNotNull(result);
        assertEquals(employeeId, result.getId());
        assertEquals("Amal Rajesh", result.getFullName());
        verify(employeeRepository, times(1)).findById(employeeId);
    }
    @Test
    void shouldReturnAllEmployees() {
        // given
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee(1L, "Amal Rajesh", "Software Engineer", "India", 80000.0));
        mockEmployees.add(new Employee(2L, "John Doe", "Manager", "USA", 120000.0));

        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        // when
        List<Employee> result = employeeService.getAllEmployees();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Amal Rajesh", result.get(0).getFullName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateEmployeeSuccessfully() {
        // given
        Long id = 1L;

        Employee existing = new Employee();
        existing.setId(id);
        existing.setFullName("Amal Rajesh");
        existing.setJobTitle("Software Engineer");
        existing.setCountry("India");
        existing.setSalary(80000.0);

        Employee incoming = new Employee();
        incoming.setFullName("Amal R");
        incoming.setJobTitle("Senior Engineer");
        incoming.setCountry("India");
        incoming.setSalary(120000.0);

        Employee saved = new Employee();
        saved.setId(id);
        saved.setFullName("Amal R");
        saved.setJobTitle("Senior Engineer");
        saved.setCountry("India");
        saved.setSalary(120000.0);

        when(employeeRepository.findById(id)).thenReturn(java.util.Optional.of(existing));
        when(employeeRepository.save(any(Employee.class))).thenReturn(saved);

        // when
        Employee result = employeeService.updateEmployee(id, incoming);

        // then
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Amal R", result.getFullName());
        assertEquals("Senior Engineer", result.getJobTitle());
        assertEquals(120000.0, result.getSalary());
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }


}
