package com.example.apijsonsearch.service;

import com.example.apijsonsearch.model.JsonDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public JsonService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<JsonDoc> searchJsonDocuments(String keyword) {
        Query query = new Query();
        query.addCriteria(Criteria.where("$**").regex(keyword, "i"));
        return mongoTemplate.find(query, JsonDoc.class);
    }
}
