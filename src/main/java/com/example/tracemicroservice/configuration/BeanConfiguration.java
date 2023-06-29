package com.example.tracemicroservice.configuration;

import com.example.tracemicroservice.adapters.driven.mongodb.adapters.RestaurantObjectsTraceMongoDBAdapter;
import com.example.tracemicroservice.adapters.driven.mongodb.adapters.TraceMongoDBAdapter;
import com.example.tracemicroservice.adapters.driven.mongodb.mappers.IRestaurantObjectsEntityMapper;
import com.example.tracemicroservice.adapters.driven.mongodb.mappers.ITraceEntityMapper;
import com.example.tracemicroservice.adapters.driven.mongodb.repository.IRestaurantTraceRepository;
import com.example.tracemicroservice.adapters.driven.mongodb.repository.ITraceRepository;
import com.example.tracemicroservice.adapters.driver.http.mapper.IRestaurantObjectsTraceRequestMapper;
import com.example.tracemicroservice.adapters.driver.http.mapper.ITraceRequestMapper;
import com.example.tracemicroservice.domain.api.IRestaurantTraceObjectsServicePort;
import com.example.tracemicroservice.domain.api.IStatisticsServicePort;
import com.example.tracemicroservice.domain.api.ITraceServicePort;
import com.example.tracemicroservice.domain.spi.IRestaurantObjectsTracePersistencePort;
import com.example.tracemicroservice.domain.spi.ITracePersistencePort;
import com.example.tracemicroservice.domain.usecase.RestaurantObjectsTraceUseCase;
import com.example.tracemicroservice.domain.usecase.StatisticsUseCase;
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
    private final IRestaurantObjectsTraceRequestMapper restaurantObjectsTraceRequestMapper;
    private final IRestaurantTraceRepository restaurantTraceRepository;
    private final IRestaurantObjectsEntityMapper restaurantObjectsEntityMapper;
    @Bean
    public ITraceServicePort traceServicePort(ITracePersistencePort tracePersistencePort){
        return new TraceUseCase(tracePersistencePort);
    }

    @Bean
    public ITracePersistencePort persistencePort( ITraceRepository traceRepository, ITraceEntityMapper traceEntityMapper) {
        return new TraceMongoDBAdapter(traceRepository,traceEntityMapper);
    }

    @Bean
    public IStatisticsServicePort statisticsServicePort(ITracePersistencePort tracePersistencePort){
        return new StatisticsUseCase(tracePersistencePort);
    }

    @Bean
    public IRestaurantObjectsTracePersistencePort restaurantObjectsTracePersistencePort( IRestaurantTraceRepository restaurantTraceRepository,    IRestaurantObjectsEntityMapper restaurantObjectsEntityMapper) {
        return new RestaurantObjectsTraceMongoDBAdapter(restaurantTraceRepository ,restaurantObjectsEntityMapper);
    }
    @Bean
    public IRestaurantTraceObjectsServicePort restaurantTraceObjectsServicePort(IRestaurantObjectsTracePersistencePort restaurantObjectsTracePersistencePort){
        return new RestaurantObjectsTraceUseCase(restaurantObjectsTracePersistencePort);
    }

}
