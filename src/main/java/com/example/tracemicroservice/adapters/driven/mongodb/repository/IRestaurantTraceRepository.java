package com.example.tracemicroservice.adapters.driven.mongodb.repository;

import com.example.tracemicroservice.adapters.driven.mongodb.entity.RestaurantObjectsTraceEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRestaurantTraceRepository  extends MongoRepository<RestaurantObjectsTraceEntity, ObjectId> {
}
