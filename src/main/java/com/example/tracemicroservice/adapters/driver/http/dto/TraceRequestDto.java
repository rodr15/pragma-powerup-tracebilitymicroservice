package com.example.tracemicroservice.adapters.driver.http.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TraceRequestDto {
    @Positive
    private Long orderId;
    @Positive
    private Long employeeId;
    @Email(message = "Invalid Email")
    private String employeeMail;
    @Positive
    private Long clientId;
    @Email(message = "Invalid Email")
    private String clientMail;

    public static final String EXAMPLE =  "{" +
            "\"orderId\":\"1\"," +
            "\"employeeId\":\"2\"," +
            "\"employeeMail\":\"employee@mail.com\"," +
            "\"clientId\":\"3\"," +
            "\"clientMail\":\"client@mail.com\"," +
            "\"currentState\":\"EARRING\"" +
            "}";
}
