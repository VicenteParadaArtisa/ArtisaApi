package com.example.demo.controller;

import com.example.demo.model.Webcube;
import com.example.demo.service.WebcubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/webcube")
@CrossOrigin(origins = "*")
public class WebcubeController {

    @Autowired
    private WebcubeService service;

    @GetMapping
    public List<Webcube> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Webcube> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Webcube create(@RequestBody Webcube webcube) {
        return service.save(webcube);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/dominio/{dominio}")
    public List<Webcube> getByDominio(@PathVariable String dominio) {
        return service.getByDominio(dominio);
    }

    @GetMapping("/marca/{marca}")
    public List<Webcube> getByMarca(@PathVariable String marca) {
        return service.getByMarca(marca);
    }

    @GetMapping("/ciudad/{ciudad}")
    public List<Webcube> getByCiudad(@PathVariable String ciudad) {
        return service.getByCiudad(ciudad);
    }

    @GetMapping("/fecha")
    public List<Webcube> getByFecha(
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hasta) {
        return service.getByFecha(desde, hasta);
    }

    @GetMapping("/velocidad/{velocidad}")
    public List<Webcube> getVelocidadMayorA(@PathVariable int velocidad) {
        return service.getVelocidadMayorA(velocidad);
    }
}
