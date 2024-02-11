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

    public Datos(int imagen, String titulo){
        this.imagen = imagen;
        this.titulo = titulo;
    }

    public Datos() {}

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
