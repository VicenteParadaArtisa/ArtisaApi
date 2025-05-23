package com.example.demo.controller;

import com.example.demo.model.Webcube;
import com.example.demo.repository.WebcubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; // Importar Map
import java.util.HashMap; // Importar HashMap
import com.fasterxml.jackson.databind.ObjectMapper; // Importar ObjectMapper

@RestController
@RequestMapping("/api/webcubes")
public class WebcubeController {

    @Autowired
    private WebcubeRepository webcubeRepository;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Usar ObjectMapper para construir JSON

    @GetMapping
    public List<Webcube> getAllWebcubes() {
        return webcubeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Webcube getWebcubeById(@PathVariable String id) {
        return webcubeRepository.findById(id).orElse(null);
    }

    @GetMapping("/search")
    public List<Webcube> searchByField(
            @RequestParam String field,
            @RequestParam String value) {

        Object queryValue;

        // Intenta inferir el tipo de dato del valor
        try {
            // Intenta como entero
            queryValue = Integer.parseInt(value);
        } catch (NumberFormatException e1) {
            try {
                // Intenta como double
                queryValue = Double.parseDouble(value);
            } catch (NumberFormatException e2) {
                // Si no es número, trátalo como String
                queryValue = value;
            }
        }

        // Construye el mapa para la consulta
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(field, queryValue);

        String jsonQuery;
        try {
            // Convierte el mapa a una cadena JSON
            jsonQuery = objectMapper.writeValueAsString(queryMap);
        } catch (Exception e) {
            // Maneja el error si no se puede convertir a JSON (poco probable aquí)
            throw new RuntimeException("Error al construir la consulta JSON", e);
        }


        // Llama al repositorio con la consulta JSON construida
        return webcubeRepository.findByCustomQuery(jsonQuery);
    }
}
