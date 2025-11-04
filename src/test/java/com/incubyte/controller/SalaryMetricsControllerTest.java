package com.incubyte.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.incubyte.service.SalaryMetricsService;

@WebMvcTest(SalaryMetricsController.class)
class SalaryMetricsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalaryMetricsService salaryMetricsService;

    @Test
    void shouldReturnCountrySalaryMetrics() throws Exception {
        when(salaryMetricsService.getCountryMetrics("India"))
                .thenReturn(new SalaryMetricsService.SalaryStats("India", 50000.0, 150000.0, 100000.0));

        mockMvc.perform(get("/metrics/country/India").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country").value("India"))
                .andExpect(jsonPath("$.min").value(50000.0))
                .andExpect(jsonPath("$.max").value(150000.0))
                .andExpect(jsonPath("$.average").value(100000.0));
    }

    @Test
    void shouldReturnAverageSalaryByJobTitle() throws Exception {
        when(salaryMetricsService.getAverageSalaryByJob("Engineer")).thenReturn(90000.0);

        mockMvc.perform(get("/metrics/job/Engineer").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("90000.0"));
    }
}
