package com.example.demo.repository;

import com.example.demo.model.Webcube;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface WebcubeRepository extends MongoRepository<Webcube, String> {

    @Query("{ 'equipo.dominio': ?0 }")
    List<Webcube> findByDominio(String dominio);

    @Query("{ 'equipo.marca': ?0 }")
    List<Webcube> findByMarca(String marca);

    @Query("{ 'ciudad': ?0 }")
    List<Webcube> findByCiudad(String ciudad);

    @Query("{ 'fecha': { $gte: ?0, $lte: ?1 } }")
    List<Webcube> findByDateBetween(Date desde, Date hasta);

    @Query("{ 'velocidad': { $gt: ?0 } }")
    List<Webcube> findByVelocidadGreaterThan(int velocidad);
}
