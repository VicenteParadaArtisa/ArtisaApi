package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "webcube")
public class Webcube {

    @Id
    private String id;

    private Instant date;
    private Ubicacion ubicacion;
    private Equipo equipo;
    private DatosDiarios datosDiarios;

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public DatosDiarios getDatosDiarios() {
        return datosDiarios;
    }

    public void setDatosDiarios(DatosDiarios datosDiarios) {
        this.datosDiarios = datosDiarios;
    }
}
