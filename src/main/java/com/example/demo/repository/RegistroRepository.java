package com.example.demo.repository;

import com.example.demo.model.Registro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroRepository extends MongoRepository<Registro, String> {

    @Query("{ 'equipo.dominio': ?0 }")
    List<Registro> buscarPorDominio(String dominio);

}
