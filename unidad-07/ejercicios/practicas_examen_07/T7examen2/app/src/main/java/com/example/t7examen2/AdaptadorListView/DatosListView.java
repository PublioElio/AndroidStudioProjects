package com.example.t7examen2.AdaptadorListView;

public class DatosListView {

    private int indice;
    private String nombre;
    private String telefono;
    private int avatar;

    public DatosListView(int indice, String nombre, String telefono, int avatar){
        this.indice = indice;
        this.nombre = nombre;
        this.telefono = telefono;
        this.avatar = avatar;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
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
