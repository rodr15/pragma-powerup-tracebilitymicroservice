package com.example.tracemicroservice.domain.api;

import com.example.tracemicroservice.adapters.driver.http.dto.TraceRequestDto;
import com.example.tracemicroservice.domain.models.Trace;

public interface ITraceServicePort {
    void saveTrace(Trace trace);
}
