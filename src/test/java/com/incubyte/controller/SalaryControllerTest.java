package com.incubyte.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.incubyte.service.SalaryService;

@WebMvcTest(SalaryController.class)
class SalaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalaryService salaryService;

    @Test
    void shouldCalculateSalaryForEmployee() throws Exception {
        Long employeeId = 1L;

        // mock response from salary service
        var breakdown = new SalaryService.SalaryBreakdown("India", 100000.0, 10000.0, 90000.0);

        when(salaryService.calculateSalaryByEmployeeId(employeeId)).thenReturn(breakdown);

        mockMvc.perform(get("/salary/{id}", employeeId)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country").value("India"))
                .andExpect(jsonPath("$.gross").value(100000.0))
                .andExpect(jsonPath("$.net").value(90000.0));
    }
}
