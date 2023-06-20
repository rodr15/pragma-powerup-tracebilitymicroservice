package com.example.tracemicroservice.domain.usecase;

import com.example.tracemicroservice.domain.api.IStatisticsServicePort;
import com.example.tracemicroservice.domain.models.EmployeeStatistics;
import com.example.tracemicroservice.domain.models.OrderStatistics;
import com.example.tracemicroservice.domain.models.OrderStatus;
import com.example.tracemicroservice.domain.models.Trace;
import com.example.tracemicroservice.domain.spi.ITracePersistencePort;
import lombok.AllArgsConstructor;

import java.time.Duration;
import java.util.List;

@AllArgsConstructor
public class StatisticsUseCase implements IStatisticsServicePort {
    private final ITracePersistencePort tracePersistencePort;

    @Override
    public List<OrderStatistics> getOrdersStatistics(List<Long> ordersId) {

        return ordersId.stream()
                .map(orderId -> {
                    Trace finished = tracePersistencePort.getTraceByOrderIdAndStatus(orderId, OrderStatus.FINISHED_ORDER)
                            .orElseThrow();

                    Duration executionTime = Duration.between(finished.getUpdatedAt(), finished.getCreatedAt());

                    return new OrderStatistics(orderId, executionTime);
                }).toList();

    }


}
