package com.example.tracemicroservice.adapters.driven.mongodb.adapters;

import com.example.tracemicroservice.adapters.driven.mongodb.mappers.ITraceEntityMapper;
import com.example.tracemicroservice.adapters.driven.mongodb.repository.ITraceRepository;
import com.example.tracemicroservice.domain.models.OrderStatus;
import com.example.tracemicroservice.domain.models.Trace;
import com.example.tracemicroservice.domain.spi.ITracePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TraceMongoDBAdapter implements ITracePersistencePort {
    private final ITraceRepository traceRepository;
    private final ITraceEntityMapper traceEntityMapper;

    @Override
    public void save(Trace trace) {
        traceRepository.save(traceEntityMapper.toEntity(trace));
    }

    @Override
    public Optional<Trace> getTrace(Long orderId) {
        return traceRepository.findByOrderId(orderId).map(traceEntityMapper::toTrace);
    }

    @Override
    public List<Trace> getAllTraceByOrderId(Long orderId) {
        return traceRepository.findAllByOrderId( orderId ).stream().map(traceEntityMapper::toTrace).toList();
    }

    @Override
    public Optional<Trace> getTraceByOrderIdAndStatus(Long orderId, OrderStatus status) {
        return traceRepository.findByOrderIdAndCurrentState( orderId, status ).map(traceEntityMapper::toTrace);
    }


}
