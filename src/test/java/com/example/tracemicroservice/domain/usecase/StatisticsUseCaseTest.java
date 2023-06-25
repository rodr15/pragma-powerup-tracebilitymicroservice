package com.example.tracemicroservice.domain.usecase;

import com.example.tracemicroservice.domain.exception.TraceNotFoundException;
import com.example.tracemicroservice.domain.models.EmployeeStatistics;
import com.example.tracemicroservice.domain.models.OrderStatistics;
import com.example.tracemicroservice.domain.models.OrderStatus;
import com.example.tracemicroservice.domain.models.Trace;
import com.example.tracemicroservice.domain.spi.ITracePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticsUseCaseTest {
    @Mock
    private ITracePersistencePort tracePersistencePort;

    private StatisticsUseCase statisticsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        statisticsUseCase = new StatisticsUseCase(tracePersistencePort);
    }

    @Test
    void getOrdersStatistics_WithValidOrdersId_ShouldReturnOrderStatisticsList() {
        // Arrange
        List<Long> ordersId = List.of(1L, 2L, 3L);
        Long employeeId = 1L;

        LocalDateTime baseDateTime = LocalDateTime.now();

        Trace trace1 = new Trace(1L, employeeId, 5L, baseDateTime.minusMinutes(5), baseDateTime.minusMinutes(10), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);
        Trace trace2 = new Trace(2L, employeeId, 6L, baseDateTime.minusMinutes(8), baseDateTime.minusMinutes(15), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);
        Trace trace3 = new Trace(3L, employeeId, 7L, baseDateTime.minusMinutes(12), baseDateTime.minusMinutes(20), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);

        when(tracePersistencePort.getTraceByOrderIdAndStatus(1L, OrderStatus.FINISHED_ORDER)).thenReturn(java.util.Optional.of(trace1));
        when(tracePersistencePort.getTraceByOrderIdAndStatus(2L, OrderStatus.FINISHED_ORDER)).thenReturn(java.util.Optional.of(trace2));
        when(tracePersistencePort.getTraceByOrderIdAndStatus(3L, OrderStatus.FINISHED_ORDER)).thenReturn(java.util.Optional.of(trace3));

        // Act
        List<OrderStatistics> orderStatisticsList = statisticsUseCase.getOrdersStatistics(ordersId);

        // Assert
        assertEquals(3, orderStatisticsList.size());
        assertEquals(1L, orderStatisticsList.get(0).getOrderId());
        assertEquals(Duration.ofMinutes(5), orderStatisticsList.get(0).getOrderExecutionTime());
        assertEquals(2L, orderStatisticsList.get(1).getOrderId());
        assertEquals(Duration.ofMinutes(7), orderStatisticsList.get(1).getOrderExecutionTime());
        assertEquals(3L, orderStatisticsList.get(2).getOrderId());
        assertEquals(Duration.ofMinutes(8), orderStatisticsList.get(2).getOrderExecutionTime());

        verify(tracePersistencePort, times(3)).getTraceByOrderIdAndStatus(anyLong(), eq(OrderStatus.FINISHED_ORDER));
    }

    @Test
    void getOrdersStatistics_WithValidOrdersId_InvalidOrderStatus_ThrowsTraceNotFoundException() {
        // Arrange
        List<Long> ordersId = List.of(1L, 2L, 3L);
        Long employeeId = 1L;

        LocalDateTime baseDateTime = LocalDateTime.now();

        Trace trace1 = new Trace(1L, employeeId, 5L, baseDateTime.minusMinutes(5), baseDateTime.minusMinutes(10), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);

        when(tracePersistencePort.getTraceByOrderIdAndStatus(1L, OrderStatus.FINISHED_ORDER)).thenReturn(java.util.Optional.of(trace1));
        when(tracePersistencePort.getTraceByOrderIdAndStatus(2L, OrderStatus.FINISHED_ORDER)).thenReturn(java.util.Optional.empty());


        // Act
        assertThrows(TraceNotFoundException.class, () -> statisticsUseCase.getOrdersStatistics(ordersId));

        verify(tracePersistencePort, times(1)).getTraceByOrderIdAndStatus(1L, OrderStatus.FINISHED_ORDER);
    }

    @Test
    void getEmployeesStatistics_WithValidEmployeesId_ShouldReturnEmployeeStatisticsList() {
        // Arrange
        List<Long> employeesId = List.of(1L, 2L, 3L);

        LocalDateTime baseDateTime = LocalDateTime.now();

        Trace trace1 = new Trace(1L, 1L, 5L, baseDateTime.minusMinutes(5), baseDateTime.minusMinutes(10), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);
        Trace trace2 = new Trace(2L, 1L, 6L, baseDateTime.minusMinutes(8), baseDateTime.minusMinutes(15), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);
        Trace trace3 = new Trace(3L, 2L, 7L, baseDateTime.minusMinutes(12), baseDateTime.minusMinutes(20), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);
        Trace trace4 = new Trace(4L, 3L, 7L, baseDateTime.minusMinutes(18), baseDateTime.minusMinutes(20), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);

        when(tracePersistencePort.getTraceByEmployeeIdAndStatus(1L, OrderStatus.FINISHED_ORDER))
                .thenReturn(List.of(trace1, trace2));
        when(tracePersistencePort.getTraceByEmployeeIdAndStatus(2L, OrderStatus.FINISHED_ORDER))
                .thenReturn(List.of(trace3));
        when(tracePersistencePort.getTraceByEmployeeIdAndStatus(3L, OrderStatus.FINISHED_ORDER))
                .thenReturn(List.of(trace4));

        // Act
        List<EmployeeStatistics> employeeStatisticsList = statisticsUseCase.getEmployeesStatistics(employeesId);
        // Assert
        assertEquals(3, employeeStatisticsList.size());
        // Assert in position order

        // POSITION: 3 | EMPLOYEE ID: 2 | AVERAGE: PT8M
        assertEquals(3L, employeeStatisticsList.get(0).getPosition());
        assertEquals(2L, employeeStatisticsList.get(0).getEmployeeId());
        assertEquals(Duration.ofMinutes(8).toNanos(), employeeStatisticsList.get(0).getAverageOrderExecutionTime().toNanos());


        // POSITION: 2 | EMPLOYEE ID: 1 | AVERAGE: PT6M
        assertEquals(2L, employeeStatisticsList.get(1).getPosition());
        assertEquals(1L, employeeStatisticsList.get(1).getEmployeeId());
        assertEquals(Duration.ofMinutes(6), employeeStatisticsList.get(1).getAverageOrderExecutionTime());

        // POSITION: 1 | EMPLOYEE ID: 3 | AVERAGE: PT2M
        assertEquals(3L, employeeStatisticsList.get(2).getEmployeeId());
        assertEquals(1L, employeeStatisticsList.get(2).getPosition());
        assertEquals(Duration.ofMinutes(2), employeeStatisticsList.get(2).getAverageOrderExecutionTime());

        verify(tracePersistencePort, times(1)).getTraceByEmployeeIdAndStatus(1L, OrderStatus.FINISHED_ORDER);
        verify(tracePersistencePort, times(1)).getTraceByEmployeeIdAndStatus(2L, OrderStatus.FINISHED_ORDER);
        verify(tracePersistencePort, times(1)).getTraceByEmployeeIdAndStatus(3L, OrderStatus.FINISHED_ORDER);
    }

    @Test
    void getEmployeesStatistics_WithValidEmployeesId_ReturnOneOTraceWithZeroExecutionTime() {
        // Arrange
        List<Long> employeesId = List.of(1L, 2L, 3L);

        LocalDateTime baseDateTime = LocalDateTime.now();

        Trace trace1 = new Trace(1L, 1L, 5L, baseDateTime.minusMinutes(5), baseDateTime.minusMinutes(10), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);
        Trace trace2 = new Trace(2L, 1L, 6L, baseDateTime.minusMinutes(8), baseDateTime.minusMinutes(15), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);
        Trace trace3 = new Trace(3L, 2L, 7L, baseDateTime.minusMinutes(12), baseDateTime.minusMinutes(12), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);
        Trace trace4 = new Trace(4L, 3L, 7L, baseDateTime.minusMinutes(18), baseDateTime.minusMinutes(20), OrderStatus.READY_ORDER, OrderStatus.FINISHED_ORDER);

        when(tracePersistencePort.getTraceByEmployeeIdAndStatus(1L, OrderStatus.FINISHED_ORDER))
                .thenReturn(List.of(trace1, trace2));
        when(tracePersistencePort.getTraceByEmployeeIdAndStatus(2L, OrderStatus.FINISHED_ORDER))
                .thenReturn(List.of(trace3));
        when(tracePersistencePort.getTraceByEmployeeIdAndStatus(3L, OrderStatus.FINISHED_ORDER))
                .thenReturn(List.of(trace4));

        // Act
        List<EmployeeStatistics> employeeStatisticsList = statisticsUseCase.getEmployeesStatistics(employeesId);

        // Assert
        assertEquals(2, employeeStatisticsList.size());

        // Assert in position order

        // POSITION: 2 | EMPLOYEE ID: 1 | AVERAGE: PT6M
        assertEquals(2L, employeeStatisticsList.get(0).getPosition());
        assertEquals(1L, employeeStatisticsList.get(0).getEmployeeId());
        assertEquals(Duration.ofMinutes(6), employeeStatisticsList.get(0).getAverageOrderExecutionTime());

        // POSITION: 1 | EMPLOYEE ID: 3 | AVERAGE: PT2M
        assertEquals(3L, employeeStatisticsList.get(1).getEmployeeId());
        assertEquals(1L, employeeStatisticsList.get(1).getPosition());
        assertEquals(Duration.ofMinutes(2), employeeStatisticsList.get(1).getAverageOrderExecutionTime());

        verify(tracePersistencePort, times(1)).getTraceByEmployeeIdAndStatus(1L, OrderStatus.FINISHED_ORDER);
        verify(tracePersistencePort, times(1)).getTraceByEmployeeIdAndStatus(2L, OrderStatus.FINISHED_ORDER);
        verify(tracePersistencePort, times(1)).getTraceByEmployeeIdAndStatus(3L, OrderStatus.FINISHED_ORDER);
    }
}