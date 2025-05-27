package com.example.apijsonsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Document(collection = "Jsons")
public class JsonDoc {

    @Id
    private String id;

    private Map<String, Object> rawJson;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getRawJson() {
        return rawJson;
    }

    public void setRawJson(Map<String, Object> rawJson) {
        this.rawJson = rawJson;
    }
}
