package com.incubyte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be greater than 0")
    private Double salary;

    // Constructors, getters, setters
    public Employee() {}

    public Employee(Long id, String fullName, String jobTitle, String country, Double salary) {
        this.id = id;
        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.country = country;
        this.salary = salary;
    }

    // Getters and Setters omitted for brevity
}
