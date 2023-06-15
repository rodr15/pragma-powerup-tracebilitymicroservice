package com.example.tracemicroservice.domain.spi;

import com.example.tracemicroservice.domain.models.Trace;

public interface ITracePersistencePort {
    void save(Trace trace);
    Trace getTrace(Long orderId);
}
