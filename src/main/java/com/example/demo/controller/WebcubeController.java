package com.example.demo.controller;

import com.example.demo.model.Webcube;
import com.example.demo.repository.WebcubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RestController
@RequestMapping("/api/webcubes")
public class WebcubeController {

    @Autowired
    private WebcubeRepository webcubeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/create")
    public ResponseEntity<Webcube> createWebcube(@RequestBody Webcube webcube) {
        try {
            Webcube savedWebcube = webcubeRepository.save(webcube);
            return new ResponseEntity<>(savedWebcube, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error creating Webcube: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Webcube>> searchWebcubes(@RequestBody Map<String, Object> searchCriteria) {
        try {
            Query query = new Query();
            for (Map.Entry<String, Object> entry : searchCriteria.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();

                if (value instanceof Map) {
                    Map<String, Object> operatorMap = (Map<String, Object>) value;
                    for (Map.Entry<String, Object> opEntry : operatorMap.entrySet()) {
                        String operator = opEntry.getKey();
                        Object opValue = opEntry.getValue();

                        switch (operator) {
                            case "$eq":
                                query.addCriteria(Criteria.where(fieldName).is(opValue));
                                break;
                            case "$gt":
                                query.addCriteria(Criteria.where(fieldName).gt(opValue));
                                break;
                            case "$gte":
                                query.addCriteria(Criteria.where(fieldName).gte(opValue));
                                break;
                            case "$lt":
                                query.addCriteria(Criteria.where(fieldName).lt(opValue));
                                break;
                            case "$lte":
                                query.addCriteria(Criteria.where(fieldName).lte(opValue));
                                break;
                            case "$regex":
                                query.addCriteria(Criteria.where(fieldName).regex((String)opValue));
                                break;
                            default:
                                System.err.println("Unsupported operator or complex value in search criteria: " + operator);
                                query.addCriteria(Criteria.where(fieldName).is(value));
                                break;
                        }
                    }
                } else {
                    query.addCriteria(Criteria.where(fieldName).is(value));
                }
            }

            List<Webcube> result = mongoTemplate.find(query, Webcube.class);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.err.println("Error searching Webcubes: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Webcube>> getAll(Pageable pageable) {
        Page<Webcube> page = webcubeRepository.findAll(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Webcube> getById(@PathVariable String id) {
        return webcubeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Webcube> update(@PathVariable String id, @RequestBody Webcube webcubeDetails) {
        return webcubeRepository.findById(id)
                .map(existingWebcube -> {
                    existingWebcube.setDate(webcubeDetails.getDate());
                    existingWebcube.setUbicacion(webcubeDetails.getUbicacion());
                    existingWebcube.setEquipo(webcubeDetails.getEquipo());
                    existingWebcube.setDatosDiarios(webcubeDetails.getDatosDiarios());
                    Webcube updated = webcubeRepository.save(existingWebcube);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (webcubeRepository.existsById(id)) {
            webcubeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-dominio/{dominio}")
    public ResponseEntity<List<Webcube>> getByDominio(@PathVariable String dominio) {
        return ResponseEntity.ok(webcubeRepository.findByDominio(dominio));
    }

    @GetMapping("/by-marca/{marca}")
    public ResponseEntity<List<Webcube>> getByMarca(@PathVariable String marca) {
        return ResponseEntity.ok(webcubeRepository.findByMarca(marca));
    }
}