package com.example.demo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import java.util.Map;

@Document(collection = "webcubes")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Webcube {
    
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    
    @JsonProperty("date")
    private Date date;
    
    @JsonProperty("ubicacion")
    private Map<String, Object> ubicacion;
    
    @JsonProperty("equipo")
    private Map<String, Object> equipo;
    
    @JsonProperty("datosDiarios")
    private Map<String, Object> datosDiarios;

    // Getters y Setters
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

    public Map<String, Object> getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Map<String, Object> ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Map<String, Object> getEquipo() {
        return equipo;
    }

    public void setEquipo(Map<String, Object> equipo) {
        this.equipo = equipo;
    }

    public Map<String, Object> getDatosDiarios() {
        return datosDiarios;
    }

    public void setDatosDiarios(Map<String, Object> datosDiarios) {
        this.datosDiarios = datosDiarios;
    }
}