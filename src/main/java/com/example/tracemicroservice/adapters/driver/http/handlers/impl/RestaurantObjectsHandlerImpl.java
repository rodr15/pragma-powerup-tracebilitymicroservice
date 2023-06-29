package com.example.tracemicroservice.adapters.driver.http.handlers.impl;

import com.example.tracemicroservice.adapters.driver.http.dto.RestaurantObjectsTraceRequestDto;
import com.example.tracemicroservice.adapters.driver.http.handlers.IRestaurantObjectsHandler;
import com.example.tracemicroservice.adapters.driver.http.mapper.IRestaurantObjectsTraceRequestMapper;
import com.example.tracemicroservice.domain.api.IRestaurantTraceObjectsServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantObjectsHandlerImpl implements IRestaurantObjectsHandler {
    private final IRestaurantTraceObjectsServicePort servicePort;
    private final IRestaurantObjectsTraceRequestMapper mapper;
    @Override
    public void saveTrace(RestaurantObjectsTraceRequestDto restaurantObjectsTraceRequestDto) {
        servicePort.saveTrace(mapper.toRestaurantObjectsTrace(restaurantObjectsTraceRequestDto));
    }

    @Override
    public void updateTrace(RestaurantObjectsTraceRequestDto restaurantObjectsTraceRequestDto) {
        servicePort.updateTrace(mapper.toRestaurantObjectsTrace(restaurantObjectsTraceRequestDto));
    }
}
