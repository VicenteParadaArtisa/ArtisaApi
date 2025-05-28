package com.example.apijsonsearch.controller;

import com.example.apijsonsearch.model.JsonDoc;
import com.example.apijsonsearch.service.JsonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.apijsonsearch.model.Equipo;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/equipo")
public class EquipoController {

    private final JsonService jsonService;

    public EquipoController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    // Endpoint: /api/equipo/dominio/{dominio}
    @GetMapping("/dominio/{dominio}")
    public ResponseEntity<?> buscarPorDominio(@PathVariable String dominio) {
        try {
            JsonDoc resultado = jsonService.buscarPorCampo("equipo.dominio", dominio);
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
            String path = "equipo.lastEvent." + campo; // Ojo: puede que solo necesites "equipo.lastEvent" directo
            JsonDoc resultado = jsonService.buscarPorCampo("equipo.lastEvent." + campo, valor);
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

    @GetMapping("/dominio/disponibles")
        public ResponseEntity<?> obtenerDominiosDisponibles() {
    try {
        List<String> dominios = jsonService.obtenerDominiosDisponibles();
        return ResponseEntity.ok(dominios);
    } catch (Exception e) {
        return ResponseEntity.status(500).body(Map.of(
            "error", "Error al obtener dominios",
            "message", e.getMessage()
        ));
    }
}


}
