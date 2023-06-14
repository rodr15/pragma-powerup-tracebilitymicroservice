package com.example.tracemicroservice.configuration;

import com.example.tracemicroservice.adapters.driver.http.mapper.ITraceRequestMapper;
import com.example.tracemicroservice.domain.api.ITraceServicePort;
import com.example.tracemicroservice.domain.usecase.TraceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITraceRequestMapper traceRequestMapper;

    @Bean
    public ITraceServicePort traceServicePort(){
        return new TraceUseCase();
    }

}
