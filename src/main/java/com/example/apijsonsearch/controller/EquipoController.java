package com.example.apijsonsearch.controller;

import com.example.apijsonsearch.service.JsonService;

import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/equipo")
public class EquipoController {

    private final JsonService equipoService;

    public EquipoController(JsonService equipoService) {
        this.equipoService = equipoService;
    }

    // Endpoint: /api/equipo/dominio/{dominio}
    @GetMapping("/dominio/{dominio}")
    public ResponseEntity<?> buscarPorDominio(@PathVariable String dominio) {
        try {
            Document resultado = equipoService.buscarPorCampo("dominio", dominio);
            if (resultado == null) {
                return ResponseEntity.status(404).body(Map.of("error", "No se encontró el dominio: " + dominio));
            }
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "error", "Error al buscar por dominio",
                "message", e.getMessage()
            ));
        }
    }

    // Endpoint: /api/equipo/lastEvent/{campo}/{valor}
    @GetMapping("/lastEvent/{campo}/{valor}")
    public ResponseEntity<?> buscarPorLastEvent(@PathVariable String campo, @PathVariable String valor) {
        try {
            String path = "datosDiarios.lastEvent." + campo;
            Document resultado = equipoService.buscarPorCampo(path, valor);
            if (resultado == null) {
                return ResponseEntity.status(404).body(Map.of("error", "No se encontró ningún documento con ese valor en lastEvent"));
            }
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "error", "Error al buscar por lastEvent",
                "message", e.getMessage()
            ));
        }
    }
}
