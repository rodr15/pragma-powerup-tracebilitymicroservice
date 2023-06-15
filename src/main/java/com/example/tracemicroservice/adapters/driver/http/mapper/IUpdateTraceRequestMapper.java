package com.example.tracemicroservice.adapters.driver.http.mapper;

import com.example.tracemicroservice.adapters.driver.http.dto.UpdateTraceRequestDto;
import com.example.tracemicroservice.domain.models.Trace;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUpdateTraceRequestMapper {
    Trace toTrace(UpdateTraceRequestDto updateTraceRequestDto);
}
