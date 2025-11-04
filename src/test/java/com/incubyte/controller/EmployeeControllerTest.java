package com.incubyte.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;
import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.model.Employee;
import com.incubyte.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateEmployeeAndReturn201() throws Exception {
		// Mock the service to return a real Employee
		Employee savedEmployee = new Employee(1L, "Amal Rajesh", "Engineer", "India", 90000.0);

		when(employeeService.createEmployee(any(Employee.class))).thenReturn(savedEmployee); // ✅ this line was missing

		mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content("""
				    {
				        "fullName": "Amal Rajesh",
				        "jobTitle": "Engineer",
				        "country": "India",
				        "salary": 90000
				    }
				""")).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.fullName").value("Amal Rajesh"))
				.andExpect(jsonPath("$.jobTitle").value("Engineer")).andExpect(jsonPath("$.country").value("India"))
				.andExpect(jsonPath("$.salary").value(90000.0));
	}
	
	@Test
    void shouldReturnAllEmployees() throws Exception {
        List<Employee> employees = List.of(
                new Employee(1L, "Amal Rajesh", "Engineer", "India", 80000.0),
                new Employee(2L, "John Doe", "Manager", "USA", 120000.0)
        );

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].fullName").value("Amal Rajesh"))
                .andExpect(jsonPath("$[1].country").value("USA"));
    }
	@Test
	void shouldUpdateEmployee() throws Exception {
	    Long employeeId = 1L;

	    Employee updated = new Employee(employeeId, "Amal Rajesh", "Senior Engineer", "India", 90000.0);

	    when(employeeService.updateEmployee(eq(employeeId), any(Employee.class))).thenReturn(updated);

	    mockMvc.perform(put("/employees/{id}", employeeId)
	            .contentType(APPLICATION_JSON)
	            .content("""
	                {
	                    "fullName": "Amal Rajesh",
	                    "jobTitle": "Senior Engineer",
	                    "country": "India",
	                    "salary": 90000.0
	                }
	            """))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.jobTitle").value("Senior Engineer"))
	            .andExpect(jsonPath("$.salary").value(90000.0));
	}

}