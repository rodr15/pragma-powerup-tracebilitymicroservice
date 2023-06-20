package com.example.tracemicroservice.domain.usecase;

import com.example.tracemicroservice.domain.api.ITraceServicePort;
import com.example.tracemicroservice.domain.exception.TraceNotFoundException;
import com.example.tracemicroservice.domain.models.Trace;
import com.example.tracemicroservice.domain.spi.ITracePersistencePort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TraceUseCase implements ITraceServicePort {
    private final ITracePersistencePort tracePersistencePort;

    @Override
    public void saveTrace(Trace trace) {
        tracePersistencePort.save(trace);
    }

    @Override
    public void updateTrace(Trace trace) {
        Trace savedTrace = tracePersistencePort.getTrace(trace.getOrderId())
                .orElseThrow(TraceNotFoundException::new);

        savedTrace.setLastState(savedTrace.getCurrentState());
        savedTrace.setCurrentState(trace.getCurrentState());
        savedTrace.setUpdatedAt(trace.getUpdatedAt());

        saveTrace(savedTrace);
    }

    @Override
    public List<Trace> getTrace(Long orderId) {
        return tracePersistencePort.getAllTraceByOrderId(orderId);
    }

}
