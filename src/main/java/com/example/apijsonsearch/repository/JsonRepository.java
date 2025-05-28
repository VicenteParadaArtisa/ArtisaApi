package com.example.apijsonsearch.repository;

import com.example.apijsonsearch.model.JsonDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JsonRepository extends MongoRepository<JsonDoc, String> {

    @Query("{ $where: 'function() { return JSON.stringify(this).includes(\"?0\"); }' }")
    List<JsonDoc> buscarPorDato(String dato);
}
