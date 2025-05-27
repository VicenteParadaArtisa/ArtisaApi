package com.example.apijsonsearch.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
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
        // Crea criterio de texto (asegúrate de tener el índice de texto creado en MongoDB)
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(keyword);
        Query query = TextQuery.queryText(textCriteria);

        return mongoTemplate.find(query, Document.class, "nombre_de_la_coleccion");
    }
}
