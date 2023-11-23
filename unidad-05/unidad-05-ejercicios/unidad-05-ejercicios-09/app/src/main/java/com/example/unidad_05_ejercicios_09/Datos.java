package com.example.unidad_05_ejercicios_09;

public class Datos {

    private String texto;
    private boolean checked;
    private int imagen;

    public Datos(int imagen, String texto, boolean checked) {
        this.imagen = imagen;
        this.texto = texto;
        this.checked = checked;
    }
    public String getTexto() {
        return texto;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getImagen() { return imagen; }

}
