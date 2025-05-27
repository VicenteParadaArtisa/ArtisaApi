package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "registros") // Asegúrate que coincide con tu colección en MongoDB
public class Registro {

    @Id
    private String id;

    private Date date;
    private Ubicacion ubicacion;
    private Equipo equipo;
    private DatosDiarios datosDiarios;

    // Constructor vacío (requerido por Spring Data)
    public Registro() {
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
