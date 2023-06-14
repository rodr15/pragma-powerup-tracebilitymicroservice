package com.example.tracemicroservice.adapters.driven.mongodb.adapters;

import com.example.tracemicroservice.adapters.driven.mongodb.entity.TraceEntity;
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
        System.out.println(traceRepository.save( traceEntityMapper.toEntity( trace ) ));
    }
}
