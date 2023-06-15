package com.example.tracemicroservice.domain.spi;

import com.example.tracemicroservice.domain.models.Trace;

import java.util.Optional;

public interface ITracePersistencePort {
    void save(Trace trace);
    Optional<Trace> getTrace(Long orderId);
}
