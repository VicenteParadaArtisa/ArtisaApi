package com.example.apijsonsearch.controller;

import com.example.apijsonsearch.service.JsonService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JsonController {

    private final JsonService jsonService;

    @Autowired
    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @PostMapping("/registro/{busqueda}")
    public List<Document> buscar(@PathVariable String busqueda) {
        return jsonService.searchJsonDocuments(busqueda);
    }
}
