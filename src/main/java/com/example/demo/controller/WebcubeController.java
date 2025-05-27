package com.example.demo.controller;

import com.example.demo.model.Webcube;
import com.example.demo.service.WebcubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/webcube")
@CrossOrigin(origins = "*")
public class WebcubeController {

    @Autowired
    private WebcubeService service;

    @GetMapping
    public ResponseEntity<List<Webcube>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Webcube> getById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Webcube> create(@RequestBody Webcube webcube) {
        return ResponseEntity.ok(service.save(webcube));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dominio/{dominio}")
    public ResponseEntity<List<Webcube>> getByDominio(@PathVariable String dominio) {
        return ResponseEntity.ok(service.getByDominio(dominio));
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Webcube>> getByMarca(@PathVariable String marca) {
        return ResponseEntity.ok(service.getByMarca(marca));
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<Webcube>> getByCiudad(@PathVariable String ciudad) {
        return ResponseEntity.ok(service.getByCiudad(ciudad));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Webcube>> getByFecha(
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hasta) {
        return ResponseEntity.ok(service.getByFecha(desde, hasta));
    }

    @GetMapping("/velocidad/{velocidad}")
    public ResponseEntity<List<Webcube>> getVelocidadMayorA(@PathVariable int velocidad) {
        return ResponseEntity.ok(service.getVelocidadMayorA(velocidad));
    }
}
