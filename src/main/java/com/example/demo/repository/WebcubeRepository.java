package com.example.demo.repository;

import com.example.demo.model.Webcube;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WebcubeRepository extends MongoRepository<Webcube, String> {

    // Dominio está dentro de equipo
    List<Webcube> findByEquipo_Dominio(String dominio);

    // Marca está dentro de equipo
    List<Webcube> findByEquipo_Marca(String marca);

    // Ciudad está dentro de ubicacion
    List<Webcube> findByUbicacion_City(String ciudad);

    // Velocidad está dentro de equipo
    List<Webcube> findByEquipo_VelocidadGreaterThan(int velocidad);

    // Date está en el nivel raíz
    List<Webcube> findByDateBetween(Date desde, Date hasta);
}
