package com.p50.libroapp.model;

import javax.persistence.*;

@Entity
public class LibroCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "librocategoria_seq")
    @SequenceGenerator(name = "librocategoria_seq", sequenceName = "librocategoria_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Libro libro;

    @ManyToOne
    private Categoria categoria;

    public LibroCategoria() {
    }

    public LibroCategoria(Libro libro, Categoria categoria) {
        this.libro = libro;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}