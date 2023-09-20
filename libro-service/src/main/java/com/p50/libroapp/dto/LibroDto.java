package com.p50.libroapp.dto;

import com.p50.libroapp.model.Autor;
import com.p50.libroapp.model.Libro;

import java.math.BigDecimal;
import java.util.List;

public class LibroDto {

    private String titulo;
    private Autor autor;
    private BigDecimal precio;
    private String estado;
    private List<Long> categoriaIds;

    public Libro toLibro() {
        Libro libro = new Libro();
        libro.setTitulo(this.titulo);
        libro.setAutor(this.autor);
        libro.setPrecio(this.precio);
        libro.setEstado(this.estado);
        return libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Long> getCategoriaIds() {
        return categoriaIds;
    }

    public void setCategoriaIds(List<Long> categoriaIds) {
        this.categoriaIds = categoriaIds;
    }
}
