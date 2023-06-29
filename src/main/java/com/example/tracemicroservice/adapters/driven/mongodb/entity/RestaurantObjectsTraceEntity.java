package com.example.tracemicroservice.adapters.driven.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "restaurant-objects-trace")
public class RestaurantObjectsTraceEntity {
    private Long restaurantId;
    private String state;
    private Long objectId;
    private String objectName;
    private String objectType;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
