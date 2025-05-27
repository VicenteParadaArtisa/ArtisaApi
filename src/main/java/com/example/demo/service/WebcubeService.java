package com.example.demo.service;

import com.example.demo.model.Webcube;
import com.example.demo.repository.WebcubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WebcubeService {

    @Autowired
    private WebcubeRepository repository;

    public List<Webcube> getAll() {
        return repository.findAll();
    }

    public Optional<Webcube> getById(String id) {
        return repository.findById(id);
    }

    public Webcube save(Webcube webcube) {
        return repository.save(webcube);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Webcube con ID " + id + " no encontrado");
        }
        repository.deleteById(id);
    }

    public List<Webcube> getByDominio(String dominio) {
        return repository.findByDominio(dominio);
    }

    public List<Webcube> getByMarca(String marca) {
        return repository.findByMarca(marca);
    }

    public List<Webcube> getByCiudad(String ciudad) {
        return repository.findByCiudad(ciudad);
    }

    public List<Webcube> getByFecha(Date desde, Date hasta) {
        return repository.findByDateBetween(desde, hasta);
    }

    public List<Webcube> getVelocidadMayorA(int velocidad) {
        return repository.findByVelocidadGreaterThan(velocidad);
    }
}
