package com.example.tracemicroservice.adapters.driver.http.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TraceRequestDto {
    @Positive
    @NotNull
    private Long orderId;
    @Positive
    private Long employeeId;
    @NotNull
    @Positive
    private Long clientId;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private String currentState;

    public static final String EXAMPLE =  "{" +
            "\"orderId\":\"1\"," +
            "\"employeeId\":\"2\"," +
            "\"clientId\":\"3\"," +
            "\"currentState\":\"EARRING_ORDER\"," +
            "\"createdAt\":\"1980-05-04T20:04:26.908597\"" +
            "}";
}
