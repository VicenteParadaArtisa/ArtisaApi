package com.example.apijsonsearch.service;

import com.example.apijsonsearch.model.JsonDoc;
import com.example.apijsonsearch.model.Equipo;
import com.example.apijsonsearch.repository.JsonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JsonService {

    private final JsonRepository jsonRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public JsonService(JsonRepository jsonRepository, MongoTemplate mongoTemplate) {
        this.jsonRepository = jsonRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<String> obtenerDominiosDisponibles() {
        List<JsonDoc> documentos = mongoTemplate.findAll(JsonDoc.class, "Jsons");

        return documentos.stream()
            .map(JsonDoc::getEquipo)
            .filter(Objects::nonNull)
            .map(Equipo::getDominio)
            .filter(Objects::nonNull)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }

    public JsonDoc buscarPorCampo(String campo, String valor) {
        return jsonRepository.buscarPorCampo(campo, valor);
    }
}
