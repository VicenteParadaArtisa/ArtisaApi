package com.example.demo.controller;

import com.example.demo.model.Webcube;
import com.example.demo.repository.WebcubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/webcubes")
public class WebcubeController {

    @Autowired
    private WebcubeRepository webcubeRepository;

    @GetMapping
    public List<Webcube> getAllWebcubes() {
        return webcubeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Webcube getWebcubeById(@PathVariable String id) {
        return webcubeRepository.findById(id).orElse(null);
    }

    @GetMapping("/search")
    public List<Webcube> searchByField(
            @RequestParam String field,
            @RequestParam String value) {
        return webcubeRepository.findByCustomQuery(String.format("{ \"%s\": \"%s\" }", field, value));
    }
}