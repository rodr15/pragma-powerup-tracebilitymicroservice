package com.example.tracemicroservice.adapters.driver.http.controller;

import com.example.tracemicroservice.adapters.driver.http.dto.RestaurantObjectsTraceRequestDto;
import com.example.tracemicroservice.adapters.driver.http.handlers.IRestaurantObjectsHandler;
import com.example.tracemicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/restaurant-trace")
@RequiredArgsConstructor
public class RestaurantObjectsTraceRestController {

    private final IRestaurantObjectsHandler restaurantObjectsHandler;

    @PostMapping("")
    public ResponseEntity<Map<String, String>> saveTrace(@Valid @RequestBody @Schema(
            description = "The request body",
            example = RestaurantObjectsTraceRequestDto.EXAMPLE
    ) RestaurantObjectsTraceRequestDto restaurantObjectsTraceRequestDto) {
        restaurantObjectsHandler.saveTrace(restaurantObjectsTraceRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.TRACE_CREATED_MESSAGE));
    }

    @PutMapping("")
    public ResponseEntity<Map<String, String>> updateTrace(@Valid @RequestBody @Schema(
            description = "The request body",
            example = RestaurantObjectsTraceRequestDto.EXAMPLE
    ) RestaurantObjectsTraceRequestDto restaurantObjectsTraceRequestDto) {
        restaurantObjectsHandler.updateTrace( restaurantObjectsTraceRequestDto );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.TRACE_CREATED_MESSAGE));
    }
}
