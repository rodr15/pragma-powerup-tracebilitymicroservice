package com.example.tracemicroservice.domain.spi;

import com.example.tracemicroservice.domain.models.OrderStatus;
import com.example.tracemicroservice.domain.models.Trace;

import java.util.List;
import java.util.Optional;

public interface ITracePersistencePort {
    void save(Trace trace);
    Optional<Trace> getTrace(Long orderId);
    List<Trace> getAllTraceByOrderId(Long orderId);
    Optional<Trace> getTraceByOrderIdAndStatus(Long orderId, OrderStatus status);
 }
