package com.example.apijsonsearch.model;

import org.bson.Document;
import org.springframework.data.annotation.Id;

// Usamos el nombre completo de la anotación para evitar conflicto
@org.springframework.data.mongodb.core.mapping.Document(collection = "Jsons")
public class JsonDoc {

    @Id
    private String id;

    private Document content;

    public JsonDoc() {}

    public JsonDoc(Document content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public Document getContent() {
        return content;
    }

    public void setContent(Document content) {
        this.content = content;
    }

    public void setId(String id) {
        this.id = id;
    }
}
