package com.example.examen_cp.listview;

public class DatosListView {
    private int id;
    private String nombre;
    private String telefono;
    private int avatar;
    public DatosListView(int id, String nombre, String telefono, int avatar) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.avatar = avatar;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public int getAvatar() {
        return avatar;
    }
    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
