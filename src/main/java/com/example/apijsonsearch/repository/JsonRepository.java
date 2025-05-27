package com.example.apijsonsearch.repository;

import com.example.apijsonsearch.model.JsonDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JsonRepository extends MongoRepository<JsonDoc, String> {
}
