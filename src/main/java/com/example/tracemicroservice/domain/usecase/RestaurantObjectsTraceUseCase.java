package com.example.tracemicroservice.domain.usecase;

import com.example.tracemicroservice.domain.api.IRestaurantTraceObjectsServicePort;
import com.example.tracemicroservice.domain.models.RestaurantObjectsTrace;
import com.example.tracemicroservice.domain.spi.IRestaurantObjectsTracePersistencePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantObjectsTraceUseCase implements IRestaurantTraceObjectsServicePort {

    private final IRestaurantObjectsTracePersistencePort persistencePort;
    @Override
    public void saveTrace(RestaurantObjectsTrace restaurantObjectsTrace) {
        persistencePort.saveTrace(restaurantObjectsTrace);
    }

    @Override
    public void updateTrace(RestaurantObjectsTrace restaurantObjectsTrace) {
        persistencePort.saveTrace(restaurantObjectsTrace);
    }
}
