package com.example.apijsonsearch.repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.apijsonsearch.model.JsonDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JsonRepository {

    private final MongoTemplate mongoTemplate;

    public JsonRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<JsonDoc> buscarPorDato(String dato) {
        Query query = new Query();
        query.addCriteria(Criteria.where("$where")
            .is("JSON.stringify(this).toLowerCase().includes('" + dato.toLowerCase() + "')"));
        return mongoTemplate.find(query, JsonDoc.class);
    }
}
