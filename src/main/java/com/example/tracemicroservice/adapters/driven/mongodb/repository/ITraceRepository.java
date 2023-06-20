package com.example.tracemicroservice.adapters.driven.mongodb.repository;

import com.example.tracemicroservice.adapters.driven.mongodb.entity.TraceEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ITraceRepository extends MongoRepository<TraceEntity, ObjectId> {
    Optional<TraceEntity> findByOrderId(Long orderId);
    List<TraceEntity> findAllByOrderId(Long orderId);
}