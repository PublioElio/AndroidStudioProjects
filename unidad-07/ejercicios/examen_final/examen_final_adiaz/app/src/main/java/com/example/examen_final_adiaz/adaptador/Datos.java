package com.example.examen_final_adiaz.adaptador;

public class Datos {
    int indice;
    int foto;
    String titulo;
    public Datos(int indice, int foto, String titulo) {
        this.indice = indice;
        this.foto = foto;
        this.titulo = titulo;
    }
    public int getIndice() {
        return indice;
    }
    public int getFoto() {
        return foto;
    }
    public String getTitulo() {
        return titulo;
    }
}
