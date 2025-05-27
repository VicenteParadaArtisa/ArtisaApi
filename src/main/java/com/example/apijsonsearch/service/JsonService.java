package com.example.apijsonsearch.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
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

    public List<Document> searchJsonDocuments(String keyword) {
        Query query = new Query();
        query.addCriteria(Criteria.where("$**").regex(keyword, "i")); // Búsqueda en cualquier campo anidado
        return mongoTemplate.find(query, Document.class, "nombre_de_la_coleccion"); // ← Cambiá este nombre
    }
}
