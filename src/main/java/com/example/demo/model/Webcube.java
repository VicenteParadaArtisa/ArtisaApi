package com.example.demo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "webcubes")
public class Webcube {

    @Id
    private ObjectId id;
    private Date date;
    private Ubicacion ubicacion;
    private Equipo equipo;
    private DatosDiarios datosDiarios;

    public String getId() {
        return id != null ? id.toHexString() : null;
    }

    public void setId(ObjectId id) {
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
