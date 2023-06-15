package com.example.tracemicroservice.domain.usecase;

import com.example.tracemicroservice.domain.api.ITraceServicePort;
import com.example.tracemicroservice.domain.models.Trace;
import com.example.tracemicroservice.domain.spi.ITracePersistencePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TraceUseCase implements ITraceServicePort {
    private final ITracePersistencePort tracePersistencePort;
    @Override
    public void saveTrace(Trace trace) {
        tracePersistencePort.save( trace );
    }

    @Override
    public void updateTrace(Trace trace){
        Trace savedTrace =  tracePersistencePort.getTrace( trace.getOrderId() );

       savedTrace.setLastState( savedTrace.getCurrentState() );
       savedTrace.setCurrentState( trace.getCurrentState() );
       savedTrace.setUpdatedAt( trace.getUpdatedAt() );

        saveTrace(trace);

    }

}
