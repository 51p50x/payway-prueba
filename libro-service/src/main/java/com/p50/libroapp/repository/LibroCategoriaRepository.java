package com.p50.libroapp.repository;

import com.p50.libroapp.model.Libro;
import com.p50.libroapp.model.LibroCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibroCategoriaRepository extends JpaRepository<LibroCategoria, Long> {
    List<LibroCategoria> findByLibro(Libro libro);
    void deleteByLibro(Libro libro);
}
