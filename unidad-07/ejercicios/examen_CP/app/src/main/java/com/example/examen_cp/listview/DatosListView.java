package com.example.examen_cp.listview;

public class DatosListView {
    private final int id;
    private final String nombre;
    private final String telefono;
    private final int avatar;
    public DatosListView(int id, String nombre, String telefono, int avatar) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.avatar = avatar;
    }
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getAvatar() {
        return avatar;
    }
}
