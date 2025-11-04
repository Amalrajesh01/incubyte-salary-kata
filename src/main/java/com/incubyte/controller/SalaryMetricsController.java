package com.incubyte.controller;

import com.incubyte.service.SalaryMetricsService;
import com.incubyte.service.SalaryMetricsService.SalaryStats;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metrics")
public class SalaryMetricsController {

    private final SalaryMetricsService salaryMetricsService;

    public SalaryMetricsController(SalaryMetricsService salaryMetricsService) {
        this.salaryMetricsService = salaryMetricsService;
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<SalaryStats> getSalaryStatsByCountry(@PathVariable String country) {
        return ResponseEntity.ok(salaryMetricsService.getSalaryStatsByCountry(country));
    }

    @GetMapping("/job/{title}")
    public ResponseEntity<Double> getAverageSalaryByJobTitle(@PathVariable String title) {
        return ResponseEntity.ok(salaryMetricsService.getAverageSalaryByJobTitle(title));
    }
}
