package com.example.tracemicroservice.domain.usecase;

import com.example.tracemicroservice.domain.api.ITraceServicePort;
import com.example.tracemicroservice.domain.exception.TraceNotFoundException;
import com.example.tracemicroservice.domain.models.Trace;
import com.example.tracemicroservice.domain.spi.ITracePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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
        Trace trace = createTrace();

        // Act
        traceUseCase.saveTrace(trace);

        // Assert
        verify(tracePersistencePort, times(1)).save(trace);
    }

    @Test
    void updateTrace_WhenTraceExists_ShouldUpdateTraceAndCallTracePersistencePortSave() {
        // Arrange
        Trace trace = createTrace();
        Trace savedTrace = createSavedTrace();

        when(tracePersistencePort.getTrace(trace.getOrderId())).thenReturn(Optional.of(savedTrace));
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
        Trace trace = createTrace();

        when(tracePersistencePort.getTrace(trace.getOrderId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TraceNotFoundException.class, () -> traceUseCase.updateTrace(trace));
    }

    private Trace createTrace() {
        Trace trace = new Trace();
        trace.setOrderId(1L);
        trace.setEmployeeId(2L);
        trace.setClientId(3L);
        trace.setUpdatedAt(LocalDate.now());
        trace.setCreatedAt(LocalDate.now());
        trace.setLastState("LAST_STATE");
        trace.setCurrentState("CURRENT_STATE");
        return trace;
    }

    private Trace createSavedTrace() {
        Trace savedTrace = createTrace();
        savedTrace.setLastState("SAVED_LAST_STATE");
        savedTrace.setCurrentState("SAVED_CURRENT_STATE");
        savedTrace.setUpdatedAt(LocalDate.now().minusDays(1));
        return savedTrace;
    }
}