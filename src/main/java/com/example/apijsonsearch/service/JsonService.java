package com.example.apijsonsearch.service;
import com.example.apijsonsearch.repository.JsonRepository;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

    private final JsonRepository jsonRepository;

    @Autowired
    public JsonService(JsonRepository jsonRepository) {
        this.jsonRepository = jsonRepository;
    }

    public Document buscarPorCampo(String campo, String valor) {
        return jsonRepository.buscarPorCampo(campo, valor);
    }
}
