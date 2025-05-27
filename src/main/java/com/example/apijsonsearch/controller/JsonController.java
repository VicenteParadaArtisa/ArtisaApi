package com.example.apijsonsearch.controller;

import com.example.apijsonsearch.model.JsonDoc;
import com.example.apijsonsearch.service.JsonService;
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

    @GetMapping("/registro/{busqueda}")
    public List<JsonDoc> buscar(@PathVariable String busqueda) {
        return jsonService.searchJsonDocuments(busqueda);
    }
}
