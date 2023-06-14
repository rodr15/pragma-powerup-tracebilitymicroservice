package com.example.tracemicroservice.domain.usecase;

import com.example.tracemicroservice.domain.api.ITraceServicePort;
import com.example.tracemicroservice.domain.models.Trace;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TraceUseCase implements ITraceServicePort {
    @Override
    public void saveTrace(Trace trace) {
        System.out.println(trace);
    }
}
