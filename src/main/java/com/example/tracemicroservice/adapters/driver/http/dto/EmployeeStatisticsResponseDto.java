package com.example.tracemicroservice.adapters.driver.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeStatisticsResponseDto {
    private Long position;
    private Long employeeId;
    private Duration averageOrderExecutionTime;
}
