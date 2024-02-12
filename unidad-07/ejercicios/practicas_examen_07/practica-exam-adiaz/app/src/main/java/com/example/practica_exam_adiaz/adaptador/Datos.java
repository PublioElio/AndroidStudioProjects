package com.example.practica_exam_adiaz.adaptador;

public class Datos {

    int indice;
    int imagen;
    String titulo;

    public Datos(int indice, int imagen, String titulo) {
        this.indice = indice;
        this.imagen = imagen;
        this.titulo = titulo;
    }

    public int getIndice() {
        return indice;
    }

    public int getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

}
