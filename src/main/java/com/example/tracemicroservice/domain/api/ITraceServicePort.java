package com.example.tracemicroservice.domain.api;

import com.example.tracemicroservice.domain.models.Trace;

import java.util.List;

public interface ITraceServicePort {
    void saveTrace(Trace trace);
    void updateTrace(Trace trace);
    List<Trace> getTrace(Long orderId);

}
