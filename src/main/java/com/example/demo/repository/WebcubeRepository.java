package com.example.demo.repository;

import com.example.demo.model.Webcube;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WebcubeRepository extends MongoRepository<Webcube, String> {
    List<Webcube> findByDominio(String dominio);
    List<Webcube> findByMarca(String marca);
    List<Webcube> findByCiudad(String ciudad);
    List<Webcube> findByDateBetween(Date desde, Date hasta);
    List<Webcube> findByVelocidadGreaterThan(int velocidad);
}
