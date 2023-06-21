package com.example.tracemicroservice.adapters.driver.http.handlers;

import com.example.tracemicroservice.adapters.driver.http.dto.EmployeeStatisticsResponseDto;
import com.example.tracemicroservice.adapters.driver.http.dto.StatisticsOrderResponseDto;

import java.util.List;

public interface IStatisticsHandler {
    List<StatisticsOrderResponseDto> getOrdersStatistics(List<Long> ordersId);
    List<EmployeeStatisticsResponseDto> getEmployeesStatistics(List<Long> employeesId);
}
