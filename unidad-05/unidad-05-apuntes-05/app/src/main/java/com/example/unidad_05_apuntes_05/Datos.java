package com.example.unidad_05_apuntes_05;

public class Datos
{
    private String texto1;
    private String texto2;
    private int imagen;

    public Datos(int imagen, String texto1, String texto2) {
        this.imagen = imagen;
        this.texto1 = texto1;
        this.texto2 = texto2;
    }

    public String getTexto1() {
        return texto1;
    }

    public void setTexto1(String texto1) {
        this.texto1 = texto1;
    }

    public String getTexto2() {
        return texto2;
    }

    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

}
