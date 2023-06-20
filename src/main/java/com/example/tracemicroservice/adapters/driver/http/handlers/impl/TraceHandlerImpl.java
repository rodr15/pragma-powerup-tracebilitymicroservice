package com.example.tracemicroservice.adapters.driver.http.handlers.impl;

import com.example.tracemicroservice.adapters.driver.http.dto.TraceRequestDto;
import com.example.tracemicroservice.adapters.driver.http.dto.TraceResponseDto;
import com.example.tracemicroservice.adapters.driver.http.dto.UpdateTraceRequestDto;
import com.example.tracemicroservice.adapters.driver.http.handlers.ITraceHandler;
import com.example.tracemicroservice.adapters.driver.http.mapper.ITraceRequestMapper;
import com.example.tracemicroservice.adapters.driver.http.mapper.IUpdateTraceRequestMapper;
import com.example.tracemicroservice.domain.api.ITraceServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraceHandlerImpl  implements ITraceHandler {
    private final ITraceRequestMapper traceRequestMapper;
    private final IUpdateTraceRequestMapper updateTraceRequestMapper;
    private final ITraceServicePort servicePort;
    @Override
    public void saveTrace(TraceRequestDto traceRequestDto) {
        servicePort.saveTrace( traceRequestMapper.toTrace( traceRequestDto ) );
    }

    @Override
    public void updateTrace(UpdateTraceRequestDto updateTraceRequestDto) {
        servicePort.updateTrace( updateTraceRequestMapper.toTrace( updateTraceRequestDto ) );
    }

    @Override
    public List<TraceResponseDto> getTrace(Long orderId) {
        return servicePort.getTrace( orderId )
                .stream()
                .map(updateTraceRequestMapper::toTraceResponseDto)
                .toList();
    }
}

