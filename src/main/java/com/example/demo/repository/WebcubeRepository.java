package com.example.demo.repository;

import com.example.demo.model.Webcube;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebcubeRepository extends MongoRepository<Webcube, String> {

    // Consulta personalizada usando JSON nativo
    @Query(value = "{'equipe.domain': ?0}")
    List<Webcube> findByDominio(String dominio);

    // Consulta por marca usando el nombre real del campo en MongoDB
    @Query(value = "{'equipe.marca': ?0}")
    List<Webcube> findByMarca(String marca);

    // Consulta por ciudad usando el nombre real del campo
    @Query(value = "{'ubtlection.city': ?0}")
    List<Webcube> findByCiudad(String ciudad);

    // Consulta genérica usando JSON string (como en tu versión original)
    @Query(value = "?0")
    List<Webcube> findByCustomQuery(String jsonQuery);

    // Consulta por rango de fechas
    @Query("{'date': {$gte: ?0, $lte: ?1}}")
    List<Webcube> findByDateBetween(Date startDate, Date endDate);

    // Consulta por velocidad mayor que
    @Query("{'equipe.velocidad': {$gt: ?0}}")
    List<Webcube> findByVelocidadGreaterThan(int velocidad);
}