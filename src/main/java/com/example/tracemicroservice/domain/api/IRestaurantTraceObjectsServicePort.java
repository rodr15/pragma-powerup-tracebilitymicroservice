package com.example.tracemicroservice.domain.api;

import com.example.tracemicroservice.domain.models.RestaurantObjectsTrace;

public interface IRestaurantTraceObjectsServicePort {
    void saveTrace(RestaurantObjectsTrace restaurantObjectsTrace);
    void updateTrace(RestaurantObjectsTrace restaurantObjectsTrace);
}
