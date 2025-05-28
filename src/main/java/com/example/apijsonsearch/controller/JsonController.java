package com.example.apijsonsearch.controller;

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
    public ResponseEntity<List<JsonDoc>> buscarPorDato(@PathVariable String dato) {
        List<JsonDoc> resultados = jsonService.buscarPorDato(dato);
        return ResponseEntity.ok(resultados);
    }
}
