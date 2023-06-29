package com.example.tracemicroservice.adapters.driven.mongodb.mappers;

import com.example.tracemicroservice.adapters.driven.mongodb.entity.RestaurantObjectsTraceEntity;
import com.example.tracemicroservice.domain.models.RestaurantObjectsTrace;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantObjectsEntityMapper {
    RestaurantObjectsTrace toRestaurantObjectsTrace(RestaurantObjectsTraceEntity restaurantObjectsTraceEntity);
    RestaurantObjectsTraceEntity toEntity(RestaurantObjectsTrace restaurantObjectsTrace);
}
