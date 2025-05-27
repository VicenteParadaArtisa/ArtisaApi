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
        if (webcube == null) {
            throw new IllegalArgumentException("El objeto Webcube no puede ser nulo.");
        }
        return repository.save(webcube);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No existe un Webcube con el ID: " + id);
        }
        repository.deleteById(id);
    }

    public List<Webcube> getByDominio(String dominio) {
        if (dominio == null || dominio.trim().isEmpty()) {
            throw new IllegalArgumentException("El dominio no puede estar vacío.");
        }
        return repository.findByDominio(dominio);
    }

    public List<Webcube> getByMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede estar vacía.");
        }
        return repository.findByMarca(marca);
    }

    public List<Webcube> getByCiudad(String ciudad) {
        if (ciudad == null || ciudad.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad no puede estar vacía.");
        }
        return repository.findByCiudad(ciudad);
    }

    public List<Webcube> getByFecha(Date desde, Date hasta) {
        if (desde == null || hasta == null || desde.after(hasta)) {
            throw new IllegalArgumentException("Fechas inválidas. 'desde' debe ser anterior o igual a 'hasta'.");
        }
        return repository.findByDateBetween(desde, hasta);
    }

    public List<Webcube> getVelocidadMayorA(int velocidad) {
        if (velocidad < 0) {
            throw new IllegalArgumentException("La velocidad debe ser mayor o igual a 0.");
        }
        return repository.findByVelocidadGreaterThan(velocidad);
    }
}
