package com.example.demo.service;

import com.example.demo.model.Registro;
import com.example.demo.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    public List<Registro> obtenerTodosLosRegistros() {
        return registroRepository.findAll();
    }

    public List<Registro> obtenerRegistrosPorDominio(String dominio) {
        return registroRepository.findByEquipoDominio(dominio);
    }

    public Registro guardarRegistro(Registro registro) {
        return registroRepository.save(registro);
    }
}
