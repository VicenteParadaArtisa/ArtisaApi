package com.example.demo.controller;

import com.example.demo.model.Registro;
import com.example.demo.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping
    public List<Registro> obtenerTodos() {
        return registroService.obtenerTodosLosRegistros();
    }

    @GetMapping("/{dominio}")
    public List<Registro> obtenerPorDominio(@PathVariable String dominio) {
        return registroService.obtenerRegistrosPorDominio(dominio);
    }

    @PostMapping
    public Registro guardar(@RequestBody Registro registro) {
        return registroService.guardarRegistro(registro);
    }
}
