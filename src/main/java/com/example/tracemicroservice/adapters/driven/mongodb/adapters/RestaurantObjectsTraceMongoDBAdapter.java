package com.example.tracemicroservice.adapters.driven.mongodb.adapters;

import com.example.tracemicroservice.adapters.driven.mongodb.mappers.IRestaurantObjectsEntityMapper;
import com.example.tracemicroservice.adapters.driven.mongodb.repository.IRestaurantTraceRepository;
import com.example.tracemicroservice.domain.models.RestaurantObjectsTrace;
import com.example.tracemicroservice.domain.spi.IRestaurantObjectsTracePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantObjectsTraceMongoDBAdapter implements IRestaurantObjectsTracePersistencePort {
    private final IRestaurantTraceRepository repository;
    private final IRestaurantObjectsEntityMapper mapper;
    @Override
    public void saveTrace(RestaurantObjectsTrace restaurantObjectsTrace) {
            repository.save(mapper.toEntity(restaurantObjectsTrace));
    }

}
