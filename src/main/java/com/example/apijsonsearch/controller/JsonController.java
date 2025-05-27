package com.example.apijsonsearch.controller;

import com.example.apijsonsearch.model.JsonDoc;
import com.example.apijsonsearch.repository.JsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@RestController
@RequestMapping("/api/registro")
public class JsonController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private JsonRepository jsonRepository;

    @GetMapping("/{valor}")
    public List<JsonDoc> buscarPorValor(@PathVariable String valor) {
        Query query = new Query();
        query.addCriteria(Criteria.where("$where")
            .is("function() { return JSON.stringify(this).toLowerCase().includes('" + valor.toLowerCase() + "'); }"));
        return mongoTemplate.find(query, JsonDoc.class);
    }

    @GetMapping
    public List<JsonDoc> obtenerTodos() {
        return jsonRepository.findAll();
    }

    @PostMapping
    public JsonDoc guardar(@RequestBody JsonDoc doc) {
        return jsonRepository.save(doc);
    }
}
