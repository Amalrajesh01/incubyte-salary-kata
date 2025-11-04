package com.incubyte.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
}