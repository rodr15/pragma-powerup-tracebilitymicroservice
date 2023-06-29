package com.example.tracemicroservice.adapters.driver.http.mapper;

import com.example.tracemicroservice.adapters.driver.http.dto.RestaurantObjectsTraceRequestDto;
import com.example.tracemicroservice.domain.models.RestaurantObjectsTrace;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantObjectsTraceRequestMapper {
    RestaurantObjectsTrace toRestaurantObjectsTrace(RestaurantObjectsTraceRequestDto restaurantObjectsTraceRequestDto);
    RestaurantObjectsTraceRequestDto toDto(RestaurantObjectsTrace restaurantObjectsTrace);
}
