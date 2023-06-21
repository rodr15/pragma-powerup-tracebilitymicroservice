package com.example.tracemicroservice.adapters.driver.http.mapper;

import com.example.tracemicroservice.adapters.driver.http.dto.EmployeeStatisticsResponseDto;
import com.example.tracemicroservice.domain.models.EmployeeStatistics;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeesStatisticsResponseMapper {
    EmployeeStatisticsResponseDto toEmployeeStatisticsResponseDto(EmployeeStatistics employeeStatistics);
}
