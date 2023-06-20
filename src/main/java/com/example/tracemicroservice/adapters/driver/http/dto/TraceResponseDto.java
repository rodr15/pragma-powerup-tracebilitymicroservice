package com.example.tracemicroservice.adapters.driver.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TraceResponseDto {
    private Long orderId;
    private Long employeeId;
    private Long clientId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String lastState;
    private String currentState;
}
