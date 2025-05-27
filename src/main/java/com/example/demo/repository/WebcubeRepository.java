package com.example.demo.repository;

import com.example.demo.model.Webcube;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WebcubeRepository extends MongoRepository<Webcube, String> {

    @Query(value = "{'equipo.dominio': ?0}")
    List<Webcube> findByDominio(String dominio);

    @Query(value = "{'equipo.marca': ?0}")
    List<Webcube> findByMarca(String marca);

    @Query(value = "{'ubicacion.city': ?0}")
    List<Webcube> findByCiudad(String ciudad);

    @Query(value = "{'date': {$gte: ?0, $lte: ?1}}")
    List<Webcube> findByDateBetween(Date start, Date end);

    @Query(value = "{'equipo.velocidad': {$gt: ?0}}")
    List<Webcube> findByVelocidadGreaterThan(int velocidad);
}
