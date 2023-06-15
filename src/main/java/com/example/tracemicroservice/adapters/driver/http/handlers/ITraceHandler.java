package com.example.tracemicroservice.adapters.driver.http.handlers;

import com.example.tracemicroservice.adapters.driver.http.dto.TraceRequestDto;
import com.example.tracemicroservice.adapters.driver.http.dto.UpdateTraceRequestDto;

public interface ITraceHandler {
    void saveTrace(TraceRequestDto traceRequestDto);
    void updateTrace(UpdateTraceRequestDto updateTraceRequestDto);
}
