package com.example.tracemicroservice.domain.usecase;

import com.example.tracemicroservice.domain.api.IStatisticsServicePort;
import com.example.tracemicroservice.domain.exception.TraceNotFoundException;
import com.example.tracemicroservice.domain.models.EmployeeStatistics;
import com.example.tracemicroservice.domain.models.OrderStatistics;
import com.example.tracemicroservice.domain.models.OrderStatus;
import com.example.tracemicroservice.domain.models.Trace;
import com.example.tracemicroservice.domain.spi.ITracePersistencePort;
import lombok.AllArgsConstructor;

import java.time.Duration;
import java.util.*;
import java.util.stream.IntStream;

@AllArgsConstructor
public class StatisticsUseCase implements IStatisticsServicePort {
    private final ITracePersistencePort tracePersistencePort;

    @Override
    public List<OrderStatistics> getOrdersStatistics(List<Long> ordersId) {

        return ordersId.stream().map(orderId -> {
            Trace finished = tracePersistencePort.getTraceByOrderIdAndStatus(orderId, OrderStatus.FINISHED_ORDER)
                    .orElseThrow(TraceNotFoundException::new);

            Duration executionTime = Duration.between(finished.getCreatedAt(), finished.getUpdatedAt());
            return new OrderStatistics(orderId, executionTime);
        }).toList();

    }

    @Override
    public List<EmployeeStatistics> getEmployeesStatistics(List<Long> employeesId) {

        Set<Long> employeesIdSet = new HashSet<>(employeesId);

        List<EmployeeStatistics> statisticsList = employeesIdSet
                .stream()
                .map(employeeId -> {
                    List<Trace> traces = tracePersistencePort.getTraceByEmployeeIdAndStatus(employeeId, OrderStatus.FINISHED_ORDER);

                    double averageNanos = calculateAverageDurationNanos(traces);

                    Duration averageDuration = Duration.ofNanos(Math.round(averageNanos));
                    if (averageNanos == 0) {
                        return null;
                    }
                    return new EmployeeStatistics(null, employeeId, averageDuration);
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(EmployeeStatistics::getAverageOrderExecutionTime).reversed())
                .toList();

        IntStream.range(0, statisticsList.size())
                .forEach(index -> statisticsList.get(index).setPosition(statisticsList.size() - (long) index));

        return statisticsList;
    }

    private double calculateAverageDurationNanos(List<Trace> traces) {
        return traces.stream().mapToLong(trace -> Duration.between(trace.getCreatedAt(), trace.getUpdatedAt()).toNanos())
                .average()
                .orElse(0);
    }
}
