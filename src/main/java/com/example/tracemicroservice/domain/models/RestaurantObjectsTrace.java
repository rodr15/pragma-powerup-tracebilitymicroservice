package com.example.tracemicroservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantObjectsTrace {
    private Long restaurantId;
    private String state;
    private String objectId;
    private String objectName;
    private String objectType;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
