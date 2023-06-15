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
    @Mapping(target = "id", source = "orderId")
    TraceEntity toEntity(Trace trace);

    @Mapping(target = "orderId", source = "id")
    Trace toTrace(TraceEntity trace);
}
