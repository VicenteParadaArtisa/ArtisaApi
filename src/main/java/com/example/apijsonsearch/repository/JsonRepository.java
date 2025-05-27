package com.example.apijsonsearch.repository;

import com.example.apijsonsearch.model.JsonDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonRepository extends MongoRepository<JsonDoc, String> {
}
