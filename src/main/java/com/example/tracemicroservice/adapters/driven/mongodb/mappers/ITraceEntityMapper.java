package com.example.tracemicroservice.adapters.driven.mongodb.mappers;

import com.example.tracemicroservice.adapters.driven.mongodb.entity.TraceEntity;
import com.example.tracemicroservice.domain.models.Trace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITraceEntityMapper {
    TraceEntity toEntity(Trace trace);
}
