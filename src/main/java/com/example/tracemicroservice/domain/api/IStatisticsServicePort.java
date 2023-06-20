package com.example.tracemicroservice.domain.api;

import com.example.tracemicroservice.domain.models.EmployeeStatistics;
import com.example.tracemicroservice.domain.models.OrderStatistics;

import java.util.List;

public interface IStatisticsServicePort {
    List<OrderStatistics> getOrdersStatistics(List<Long> ordersId);

}
