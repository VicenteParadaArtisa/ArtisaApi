package com.example.apijsonsearch.service;

import com.example.apijsonsearch.model.JsonDoc;
import com.example.apijsonsearch.repository.JsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apijsonsearch.model.Equipo;

import java.util.List;

@Service
public class JsonService {

    private final JsonRepository jsonRepository;

    @Autowired
    public JsonService(JsonRepository jsonRepository) {
        this.jsonRepository = jsonRepository;
    }

    /**
     * Búsqueda específica por dominio (equipo.dominio)
     */
    public JsonDoc buscarPorDominio(String dominio) {
        return jsonRepository.buscarPorDominio(dominio);
    }

    /**
     * Búsqueda genérica por cualquier campo (puede ser anidado)
     */
    public JsonDoc buscarPorCampo(String campo, String valor) {
        return jsonRepository.buscarPorCampo(campo, valor);
    }

    public List<Equipo> obtenerEquiposDisponibles() {
        return jsonRepository.obtenerEquiposUnicosOrdenados();
    }
    
}
