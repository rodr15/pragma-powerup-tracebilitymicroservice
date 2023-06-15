package com.example.tracemicroservice.adapters.driven.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "trace")
public class TraceEntity {
    private Long id;
    private Long employeeId;
    private Long clientId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String lastState;
    private String currentState;
}
