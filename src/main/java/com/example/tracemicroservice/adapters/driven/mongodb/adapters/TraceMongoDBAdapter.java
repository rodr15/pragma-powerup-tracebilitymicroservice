package com.example.tracemicroservice.adapters.driven.mongodb.adapters;

import com.example.tracemicroservice.adapters.driven.mongodb.mappers.ITraceEntityMapper;
import com.example.tracemicroservice.adapters.driven.mongodb.repository.ITraceRepository;
import com.example.tracemicroservice.domain.models.Trace;
import com.example.tracemicroservice.domain.spi.ITracePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceMongoDBAdapter implements ITracePersistencePort {
    private final ITraceRepository traceRepository;
    private final ITraceEntityMapper traceEntityMapper;

    @Override
    public void save(Trace trace) {
        traceRepository.save( traceEntityMapper.toEntity( trace ) );
    }

    @Override
    public Trace getTrace(Long orderId) {
        return traceEntityMapper.toTrace( traceRepository.findById(orderId) );
    }


}
