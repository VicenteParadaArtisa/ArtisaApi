package com.example.demo.model;

public class Ubicacion {

    private double latitude1;
    private double longitude1;
    private double latitude2;
    private double longitude2;
    private double latitude3;
    private double longitude3;
    private String city;
    private String ubicacion_descripcion;

    public Ubicacion() {}

    // Getters y Setters
    public double getLatitude1() {
        return latitude1;
    }

    public void setLatitude1(double latitude1) {
        this.latitude1 = latitude1;
    }

    public double getLongitude1() {
        return longitude1;
    }

    public void setLongitude1(double longitude1) {
        this.longitude1 = longitude1;
    }

    public double getLatitude2() {
        return latitude2;
    }

    public void setLatitude2(double latitude2) {
        this.latitude2 = latitude2;
    }

    public double getLongitude2() {
        return longitude2;
    }

    public void setLongitude2(double longitude2) {
        this.longitude2 = longitude2;
    }

    public double getLatitude3() {
        return latitude3;
    }

    public void setLatitude3(double latitude3) {
        this.latitude3 = latitude3;
    }

    public double getLongitude3() {
        return longitude3;
    }

    public void setLongitude3(double longitude3) {
        this.longitude3 = longitude3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUbicacion_descripcion() {
        return ubicacion_descripcion;
    }

    public void setUbicacion_descripcion(String ubicacion_descripcion) {
        this.ubicacion_descripcion = ubicacion_descripcion;
    }
}
