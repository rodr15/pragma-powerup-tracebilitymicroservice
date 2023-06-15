package com.example.tracemicroservice.adapters.driven.mongodb.repository;

import com.example.tracemicroservice.adapters.driven.mongodb.entity.TraceEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITraceRepository extends MongoRepository<TraceEntity, ObjectId> {
    TraceEntity findById(Long orderId);
}