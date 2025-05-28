package com.example.apijsonsearch.service;

import com.example.apijsonsearch.exception.ResourceNotFoundException;
import com.example.apijsonsearch.model.JsonDoc;
import com.example.apijsonsearch.repository.JsonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonService {

    private final JsonRepository jsonRepository;

    public JsonService(JsonRepository jsonRepository) {
        this.jsonRepository = jsonRepository;
    }

    public List<JsonDoc> buscarPorDato(String dato) {
        try {
            return jsonRepository.buscarPorDato(dato);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar en MongoDB: " + e.getMessage(), e);
        }
    }
}
