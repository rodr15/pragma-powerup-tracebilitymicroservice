package com.example.tracemicroservice.adapters.driver.http.handlers;

import com.example.tracemicroservice.adapters.driver.http.dto.TraceRequestDto;

public interface ITraceHandler {
    void saveTrace(TraceRequestDto traceRequestDto);
}
