package com.example.demo.repository;

import com.example.demo.model.Registro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroRepository extends MongoRepository<Registro, String> {
    List<Registro> findByEquipoDominio(String dominio);
}
