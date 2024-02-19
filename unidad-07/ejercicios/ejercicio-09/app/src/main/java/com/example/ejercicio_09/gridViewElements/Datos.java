package com.example.ejercicio_09.gridViewElements;

public class Datos {

    public int id;

    public String version;
    public int year;

    public Datos(int id, String version, int year) {
        this.id = id;
        this.version = version;
        this.year = year;
    }

    public Datos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
