package com.example.tracemicroservice.configuration;

import com.example.tracemicroservice.adapters.driven.mongodb.adapters.TraceMongoDBAdapter;
import com.example.tracemicroservice.adapters.driven.mongodb.mappers.ITraceEntityMapper;
import com.example.tracemicroservice.adapters.driven.mongodb.repository.ITraceRepository;
import com.example.tracemicroservice.adapters.driver.http.mapper.ITraceRequestMapper;
import com.example.tracemicroservice.domain.api.ITraceServicePort;
import com.example.tracemicroservice.domain.spi.ITracePersistencePort;
import com.example.tracemicroservice.domain.usecase.TraceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITraceRequestMapper traceRequestMapper;
    private final ITraceRepository traceRepository;
    private final ITraceEntityMapper traceEntityMapper;
    @Bean
    public ITraceServicePort traceServicePort(ITracePersistencePort tracePersistencePort){
        return new TraceUseCase(tracePersistencePort);
    }

    @Bean
    public ITracePersistencePort persistencePort( ITraceRepository traceRepository, ITraceEntityMapper traceEntityMapper) {
        return new TraceMongoDBAdapter(traceRepository,traceEntityMapper);
    }
}
