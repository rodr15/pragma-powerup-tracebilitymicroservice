package com.example.tracemicroservice.adapters.driver.http.controller;

import com.example.tracemicroservice.adapters.driver.http.dto.EmployeeStatisticsResponseDto;
import com.example.tracemicroservice.adapters.driver.http.dto.StatisticsOrderResponseDto;
import com.example.tracemicroservice.adapters.driver.http.handlers.IStatisticsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsRestController {
    private final IStatisticsHandler statisticsHandler;

    @GetMapping("orders")
    public ResponseEntity<List<StatisticsOrderResponseDto>> getOrderStatistics(@RequestParam List<Long> ordersId) {
        return ResponseEntity.ok(statisticsHandler.getOrdersStatistics(ordersId));
    }

    @GetMapping("employee")
    public ResponseEntity<List<EmployeeStatisticsResponseDto>> getEmployeeStatistics(@RequestParam List<Long> employeesId) {
        return ResponseEntity.ok(statisticsHandler.getEmployeesStatistics(employeesId));
    }
}
