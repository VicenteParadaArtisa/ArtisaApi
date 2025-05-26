package com.example.demo.repository;

import com.example.demo.model.Webcube;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebcubeRepository extends MongoRepository<Webcube, String> {

    @Query(value = "?0", fields = "{}")
    List<Webcube> findByCustomQuery(String query);

    // Buscar por dominio del vehículo
    List<Webcube> findByEquipo_Dominio(String dominio);

    // Buscar por ciudad en ubicación
    List<Webcube> findByUbicacion_City(String city);

    // Buscar por marca del equipo
    List<Webcube> findByEquipo_Marca(String marca);
}