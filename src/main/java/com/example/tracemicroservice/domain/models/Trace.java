package com.example.tracemicroservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trace {
    private Long orderId;
    private Long employeeId;
    private Long clientId;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private OrderStatus lastState;
    private OrderStatus currentState;

    @Override
    public String toString() {
        return "Trace{" +
                ", orderId=" + orderId +
                ", employeeId=" + employeeId +
                ", clientId=" + clientId +
                ", updatedAt=" + updatedAt +
                ", lastState='" + lastState +
                ", currentState='" + currentState +
                '}';
    }
}
