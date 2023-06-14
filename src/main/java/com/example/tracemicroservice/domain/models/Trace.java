package com.example.tracemicroservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trace {
    private Long orderId;
    private Long employeeId;
    private Long clientId;
    private String employeeMail;
    private String clientMail;
    private LocalDate updatedAt;
    private String lastState;
    private String currentState;

    @Override
    public String toString() {
        return "Trace{" +
//                "id=" + id +
                ", orderId=" + orderId +
                ", employeeId=" + employeeId +
                ", clientId=" + clientId +
                ", employeeMail='" + employeeMail + '\'' +
                ", clientMail='" + clientMail + '\'' +
                ", updatedAt=" + updatedAt +
                ", lastState='" + lastState + '\'' +
                ", currentState='" + currentState + '\'' +
                '}';
    }
}
