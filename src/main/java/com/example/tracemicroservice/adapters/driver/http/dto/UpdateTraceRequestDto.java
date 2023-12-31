package com.example.tracemicroservice.adapters.driver.http.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UpdateTraceRequestDto {
    @Positive
    @NotNull
    private Long orderId;
    @Positive
    @NotNull
    private Long employeeId;
    @NotNull
    private LocalDateTime updatedAt;
    @NotNull
    private String currentState;

    public static final String EXAMPLE =  "{" +
            "\"orderId\":\"1\"," +
            "\"currentState\":\"EARRING\"," +
            "\"updatedAt\":\"1980-05-04T20:04:26.908597\"" +
            "}";

}
