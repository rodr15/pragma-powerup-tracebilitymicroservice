package com.example.tracemicroservice.adapters.driver.http.handlers;

import com.example.tracemicroservice.adapters.driver.http.dto.TraceRequestDto;
import com.example.tracemicroservice.adapters.driver.http.dto.TraceResponseDto;
import com.example.tracemicroservice.adapters.driver.http.dto.UpdateTraceRequestDto;

import java.util.List;

public interface ITraceHandler {
    void saveTrace(TraceRequestDto traceRequestDto);
    void updateTrace(UpdateTraceRequestDto updateTraceRequestDto);
    List<TraceResponseDto> getTrace(Long orderId);
}
