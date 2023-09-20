package com.p50.libroapp.controller;

import com.p50.libroapp.dto.LibroDto;
import com.p50.libroapp.exception.ResourceNotFoundException;
import com.p50.libroapp.model.Libro;
import com.p50.libroapp.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping(value = "/libros", produces = "application/json")
    public ResponseEntity<List<Libro>> getAllLibros(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Libro> libros = libroService.findAll(page, size);
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Libro libro = libroService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Libro no encontrado con ID: " + id));
        return ResponseEntity.ok(libro);
    }

    @PostMapping("/libros")
    public ResponseEntity<Libro> createLibro(@RequestBody LibroDto libroDto) {
        Libro libro = new Libro();
        libro.setTitulo(libroDto.getTitulo());
        libro.setAutor(libroDto.getAutor());
        libro.setPrecio(libroDto.getPrecio());
        libro.setEstado(libroDto.getEstado());
        libro = libroService.create(libro, libroDto.getCategoriaIds());
        return ResponseEntity.ok(libro);
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody LibroDto libroDto) {
        Libro libro = libroService.update(id, libroDto.toLibro(), libroDto.getCategoriaIds());
        return ResponseEntity.ok(libro);
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<?> deleteLibro(@PathVariable Long id) {
        libroService.delete(id);
        return ResponseEntity.ok().build();
    }
}