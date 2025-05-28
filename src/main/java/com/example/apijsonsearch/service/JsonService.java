package com.example.apijsonsearch.service;

import com.example.apijsonsearch.model.JsonDoc;
import com.example.apijsonsearch.repository.JsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

    private final JsonRepository jsonRepository;

    @Autowired
    public JsonService(JsonRepository jsonRepository) {
        this.jsonRepository = jsonRepository;
    }

    public JsonDoc buscarPorCampo(String campo, String valor) {
        return jsonRepository.buscarPorCampo(campo, valor);
    }
}
