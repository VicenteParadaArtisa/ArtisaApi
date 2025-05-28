package com.example.apijsonsearch.controller;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.HashMap;
import java.time.Instant;

import com.example.apijsonsearch.model.JsonDoc;
import com.example.apijsonsearch.service.JsonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JsonController {

    private final JsonService jsonService;

    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @GetMapping("/busqueda/{dato}")
    public ResponseEntity<?> buscarDato(@PathVariable String dato) {
        List<JsonDoc> resultados = jsonService.buscarPorDato(dato);
        if (resultados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "No se encontraron resultados para: " + dato));
        }
        return ResponseEntity.ok(resultados);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejarErrores(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", Instant.now());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage());
        error.put("path", "/api/busqueda/{dato}");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
