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
public class RestaurantObjectsTraceRequestDto {
    private Long restaurantId;
    private String state;
    private String objectId;
    private String objectName;
    private String objectType;
    private LocalDateTime updatedAt;
    private Long updatedBy;


    public static final String EXAMPLE =  "{" +
            "\"restaurantId\":\"100\"," +
            "\"state\":\"ACTIVE\"," +
            "\"objectId\":\"3\"," +
            "\"objectName\":\"SALCHIPAPAS\"," +
            "\"objectType\":\"RESTAURANT\"," +
            "\"updatedBy\":\"200\"," +
            "\"updatedAt\":\"1980-05-04T20:04:26.908597\"" +
            "}";

}
