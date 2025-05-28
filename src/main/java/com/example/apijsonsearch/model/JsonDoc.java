package com.example.apijsonsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "tu_coleccion")  // Cambiá "tu_coleccion" por el nombre real en MongoDB
public class JsonDoc {

    @Id
    private String id;

    private Map<String, Object> data;  // captura todo el documento como JSON genérico

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
