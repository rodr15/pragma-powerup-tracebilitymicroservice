package com.example.tracemicroservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeStatistics {
    private Long position;
    private Long employeeId;
    private Duration averageOrderExecutionTime;
}
