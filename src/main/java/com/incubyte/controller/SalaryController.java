package com.incubyte.controller;

import com.incubyte.service.SalaryService;
import com.incubyte.service.SalaryService.SalaryBreakdown;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSalaryByEmployeeId(@PathVariable Long id) {
        try {
            SalaryBreakdown breakdown = salaryService.calculateSalaryByEmployeeId(id);
            return ResponseEntity.ok(breakdown);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Something went wrong while calculating salary.");
        }
    }
}
