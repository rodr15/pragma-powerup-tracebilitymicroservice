package com.example.tracemicroservice.adapters.driver.http.handlers.impl;

import com.example.tracemicroservice.adapters.driver.http.dto.TraceRequestDto;
import com.example.tracemicroservice.adapters.driver.http.handlers.ITraceHandler;
import com.example.tracemicroservice.adapters.driver.http.mapper.ITraceRequestMapper;
import com.example.tracemicroservice.domain.api.ITraceServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraceHandlerImpl  implements ITraceHandler {
    private final ITraceRequestMapper requestMapper;
    private final ITraceServicePort servicePort;
    @Override
    public void saveTrace(TraceRequestDto traceRequestDto) {
        servicePort.saveTrace( requestMapper.toTrace( traceRequestDto ) );
    }
}

