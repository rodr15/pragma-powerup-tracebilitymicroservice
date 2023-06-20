package com.example.tracemicroservice.adapters.driver.http.handlers.impl;

import com.example.tracemicroservice.adapters.driver.http.dto.StatisticsOrderResponseDto;
import com.example.tracemicroservice.adapters.driver.http.handlers.IStatisticsHandler;
import com.example.tracemicroservice.adapters.driver.http.mapper.IStatisticsOrderResponseMapper;
import com.example.tracemicroservice.domain.api.IStatisticsServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsHandlerImpl implements IStatisticsHandler {
    private final IStatisticsServicePort servicePort;
    private final IStatisticsOrderResponseMapper responseMapper;

    @Override
    public List<StatisticsOrderResponseDto> getOrdersStatistics(List<Long> ordersId) {
        return servicePort.getOrdersStatistics(ordersId).stream().map(responseMapper::toStatisticsOrderResponseDto).toList();
    }
}
