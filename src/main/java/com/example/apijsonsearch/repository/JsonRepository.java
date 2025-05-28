package com.example.apijsonsearch.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class JsonRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public JsonRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Document buscarPorCampo(String campo, String valor) {
        Query query = new Query(Criteria.where(campo).is(valor));
        return mongoTemplate.findOne(query, Document.class, "jsondoc");
    }
}
