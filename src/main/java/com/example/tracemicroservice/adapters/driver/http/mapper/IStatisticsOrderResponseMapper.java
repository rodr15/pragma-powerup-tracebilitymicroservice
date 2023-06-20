package com.example.tracemicroservice.adapters.driver.http.mapper;

import com.example.tracemicroservice.adapters.driver.http.dto.StatisticsOrderResponseDto;
import com.example.tracemicroservice.domain.models.OrderStatistics;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IStatisticsOrderResponseMapper {
    StatisticsOrderResponseDto toStatisticsOrderResponseDto(OrderStatistics orderStatistics);
}
