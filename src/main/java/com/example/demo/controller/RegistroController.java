package com.example.demo.controller;

import com.example.demo.model.Registro;
import com.example.demo.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {

    @Autowired
    private RegistroRepository registroRepository;

    @GetMapping
    public List<Registro> obtenerTodos() {
        return registroRepository.findAll();
    }

    @GetMapping("/{dominio}")
    public List<Registro> obtenerPorDominio(@PathVariable String dominio) {
        return registroRepository.buscarPorDominio(dominio);
    }

    @PostMapping
    public Registro guardar(@RequestBody Registro registro) {
        return registroRepository.save(registro);
    }
}
