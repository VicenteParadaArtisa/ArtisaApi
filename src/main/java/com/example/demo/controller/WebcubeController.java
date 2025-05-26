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
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/webcubes")
public class WebcubeController {

    @Autowired
    private WebcubeRepository webcubeRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public ResponseEntity<Webcube> create(@RequestBody Webcube webcube) {
        try {
            Webcube savedWebcube = webcubeRepository.save(webcube);
            return new ResponseEntity<>(savedWebcube, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
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

    @GetMapping("/search")
    public ResponseEntity<List<Webcube>> searchByField(
            @RequestParam String field,
            @RequestParam String value) {
        try {
            Object queryValue = parseValue(value);
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put(field, queryValue);
            String jsonQuery = objectMapper.writeValueAsString(queryMap);
            return ResponseEntity.ok(webcubeRepository.findByCustomQuery(jsonQuery));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/by-dominio/{dominio}")
    public ResponseEntity<List<Webcube>> getByDominio(@PathVariable String dominio) {
        return ResponseEntity.ok(webcubeRepository.findByDominio(dominio));
    }

    @GetMapping("/by-marca/{marca}")
    public ResponseEntity<List<Webcube>> getByMarca(@PathVariable String marca) {
        return ResponseEntity.ok(webcubeRepository.findByMarca(marca));
    }

    private Object parseValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e1) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e2) {
                return value;
            }
        }
    }
}