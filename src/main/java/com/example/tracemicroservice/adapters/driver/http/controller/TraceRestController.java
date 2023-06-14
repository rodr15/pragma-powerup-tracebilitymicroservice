package com.example.tracemicroservice.adapters.driver.http.controller;

import com.example.tracemicroservice.adapters.driver.http.dto.TraceRequestDto;
import com.example.tracemicroservice.adapters.driver.http.handlers.ITraceHandler;
import com.example.tracemicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/trace")
@RequiredArgsConstructor
public class TraceRestController {

    private final ITraceHandler traceHandler;

    @PostMapping("")
    public ResponseEntity<Map<String, String>> saveTrace(@Valid @RequestBody @Schema(
            description = "The request body",
            example = TraceRequestDto.EXAMPLE
    ) TraceRequestDto traceRequestDto) {
        traceHandler.saveTrace(traceRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.TRACE_CREATED_MESSAGE));
    }
}