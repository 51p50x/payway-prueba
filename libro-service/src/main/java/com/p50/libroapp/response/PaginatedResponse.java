package com.p50.libroapp.response;

import com.p50.libroapp.model.Libro;

import java.util.List;

public class PaginatedResponse {
    private List<Libro> libros;
    private long totalRegistros;
    private int totalPaginas;

    public PaginatedResponse(List<Libro> libros, long totalRegistros, int totalPaginas) {
        this.libros = libros;
        this.totalRegistros = totalRegistros;
        this.totalPaginas = totalPaginas;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public long getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(long totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }
}
