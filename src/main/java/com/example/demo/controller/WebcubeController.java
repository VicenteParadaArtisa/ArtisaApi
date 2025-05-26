package com.example.demo.controller;

import com.example.demo.model.Webcube;
import com.example.demo.repository.WebcubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/webcubes")
public class WebcubeController {

    @Autowired
    private WebcubeRepository webcubeRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // CREATE - Añadir un nuevo vehículo
    @PostMapping
    public ResponseEntity<Webcube> createWebcube(@RequestBody Webcube webcube) {
        try {
            Webcube newWebcube = webcubeRepository.save(webcube);
            return new ResponseEntity<>(newWebcube, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Obtener todos los vehículos
    @GetMapping
    public ResponseEntity<List<Webcube>> getAllWebcubes() {
        try {
            List<Webcube> webcubes = webcubeRepository.findAll();
            return new ResponseEntity<>(webcubes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Obtener un vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Webcube> getWebcubeById(@PathVariable String id) {
        try {
            Webcube webcube = webcubeRepository.findById(id).orElse(null);
            if (webcube != null) {
                return new ResponseEntity<>(webcube, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE - Actualizar un vehículo existente
    @PutMapping("/{id}")
    public ResponseEntity<Webcube> updateWebcube(@PathVariable String id, @RequestBody Webcube webcubeDetails) {
        try {
            Webcube existingWebcube = webcubeRepository.findById(id).orElse(null);
            if (existingWebcube == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Actualizar todos los campos
            existingWebcube.setDate(webcubeDetails.getDate());
            existingWebcube.setUbicacion(webcubeDetails.getUbicacion());
            existingWebcube.setEquipo(webcubeDetails.getEquipo());
            existingWebcube.setDatosDiarios(webcubeDetails.getDatosDiarios());

            Webcube updatedWebcube = webcubeRepository.save(existingWebcube);
            return new ResponseEntity<>(updatedWebcube, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE - Eliminar un vehículo
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWebcube(@PathVariable String id) {
        try {
            webcubeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Búsqueda por campo específico (ya existente)
    @GetMapping("/search")
    public ResponseEntity<List<Webcube>> searchByField(
            @RequestParam String field,
            @RequestParam String value) {

        try {
            Object queryValue;

            // Intenta inferir el tipo de dato del valor
            try {
                queryValue = Integer.parseInt(value);
            } catch (NumberFormatException e1) {
                try {
                    queryValue = Double.parseDouble(value);
                } catch (NumberFormatException e2) {
                    queryValue = value;
                }
            }

            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put(field, queryValue);

            String jsonQuery = objectMapper.writeValueAsString(queryMap);
            List<Webcube> results = webcubeRepository.findByCustomQuery(jsonQuery);
            
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}