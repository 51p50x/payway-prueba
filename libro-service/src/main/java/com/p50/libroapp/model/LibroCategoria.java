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

}