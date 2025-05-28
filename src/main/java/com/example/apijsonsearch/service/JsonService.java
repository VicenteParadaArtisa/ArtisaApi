package com.example.apijsonsearch.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public JsonService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Document buscarPorCampo(String campo, String valor) {
        Query query = new Query(Criteria.where(campo).is(valor));
        return mongoTemplate.findOne(query, Document.class, "jsons");
    }
    
}
