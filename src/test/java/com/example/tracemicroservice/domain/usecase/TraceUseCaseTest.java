package com.example.tracemicroservice.domain.usecase;

import com.example.tracemicroservice.domain.api.ITraceServicePort;
import com.example.tracemicroservice.domain.exception.TraceNotFoundException;
import com.example.tracemicroservice.domain.models.OrderStatus;
import com.example.tracemicroservice.domain.models.Trace;
import com.example.tracemicroservice.domain.spi.ITracePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TraceUseCaseTest {

    @Mock
    private ITracePersistencePort tracePersistencePort;

    private ITraceServicePort traceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        traceUseCase = new TraceUseCase(tracePersistencePort);
    }

    @Test
    void saveTrace_ShouldCallTracePersistencePortSave() {
        // Arrange
        Trace savedTrace = createSavedTrace();
        Trace trace = createTrace(savedTrace);

        // Act
        traceUseCase.saveTrace(trace);

        // Assert
        verify(tracePersistencePort, times(1)).save(trace);
    }

    @Test
    void updateTrace_WhenTraceExists_ShouldUpdateTraceAndCallTracePersistencePortSave() {
        // Arrange
        Trace savedTrace = createSavedTrace();
        Trace trace = createTrace(savedTrace);

        when(tracePersistencePort.getTraceByOrderIdAndStatus(trace.getOrderId(),trace.getCurrentState().before()))
                .thenReturn(Optional.of(savedTrace));
        // Act
        traceUseCase.updateTrace(trace);

        // Assert
        verify(tracePersistencePort, times(1)).save(savedTrace);

        assertEquals(trace.getCurrentState(), savedTrace.getCurrentState());
        assertEquals(trace.getUpdatedAt(), savedTrace.getUpdatedAt());

    }

    @Test
    void updateTrace_WhenTraceDoesNotExist_ShouldThrowTraceNotFoundException() {
        // Arrange
        Trace savedTrace = createSavedTrace();
        Trace trace = createTrace(savedTrace);

        when(tracePersistencePort.getTraceByOrderIdAndStatus(trace.getOrderId(),trace.getCurrentState().before()))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TraceNotFoundException.class, () -> traceUseCase.updateTrace(trace));
    }

    private Trace createTrace(Trace savedTrace) {
        Trace trace = new Trace();
        trace.setOrderId(savedTrace.getOrderId());
        trace.setEmployeeId(savedTrace.getEmployeeId());
        trace.setClientId(savedTrace.getClientId());
        trace.setUpdatedAt(LocalDateTime.now());
        trace.setCreatedAt(savedTrace.getCreatedAt());
        trace.setLastState(savedTrace.getCurrentState());
        trace.setCurrentState(savedTrace.getCurrentState().next());
        return trace;
    }

    private Trace createSavedTrace() {
        Trace savedTrace = new Trace();
        savedTrace.setOrderId(1L);
        savedTrace.setEmployeeId(2L);
        savedTrace.setClientId(3L);
        savedTrace.setCreatedAt(LocalDateTime.now().minusDays(2));
        savedTrace.setUpdatedAt(LocalDateTime.now().minusDays(1));
        savedTrace.setLastState(OrderStatus.IN_PREPARATION_ORDER);
        savedTrace.setCurrentState(OrderStatus.READY_ORDER);
        return savedTrace;
    }

    @Test
    void testGetTrace() {
        // Arrange
        Long orderId = 1L;
        Trace trace1 = new Trace();
        Trace trace2 = new Trace();
        Trace trace3 = new Trace();
        when(tracePersistencePort.getAllTraceByOrderId(any(Long.class)))
                .thenReturn(List.of(trace1, trace2, trace3));

        // Act
        List<Trace> traceList = traceUseCase.getTrace(orderId);

        // Assert
        assertEquals(3, traceList.size());
        assertEquals(trace1, traceList.get(0));
        assertEquals(trace2, traceList.get(1));
        assertEquals(trace3, traceList.get(2));
    }

}