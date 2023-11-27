package com.example.unidad_05_ejercicios_09;

public class Datos {

    private String texto;
    private String texto2;
    private boolean checked;
    private int imagen;

    public Datos(int imagen, String texto, String texto2, boolean checked) {
        this.imagen = imagen;
        this.texto = texto;
        this.texto2 = texto2;
        this.checked = checked;
    }
    public String getTexto() {
        return texto;
    }

    public String getTexto2() {
        return texto2;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getImagen() { return imagen; }

}
