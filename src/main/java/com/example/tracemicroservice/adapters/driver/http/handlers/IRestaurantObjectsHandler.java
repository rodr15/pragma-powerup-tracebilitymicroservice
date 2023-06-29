package com.example.tracemicroservice.adapters.driver.http.handlers;

import com.example.tracemicroservice.adapters.driver.http.dto.RestaurantObjectsTraceRequestDto;

public interface IRestaurantObjectsHandler {
    void saveTrace(RestaurantObjectsTraceRequestDto restaurantObjectsTraceRequestDto);
    void updateTrace(RestaurantObjectsTraceRequestDto restaurantObjectsTraceRequestDto);
}