package com.example.tracemicroservice.domain.spi;

import com.example.tracemicroservice.domain.models.RestaurantObjectsTrace;

public interface IRestaurantObjectsTracePersistencePort {
    void saveTrace(RestaurantObjectsTrace restaurantObjectsTrace);
}
