package com.p50.libroapp.controller;

import com.p50.libroapp.dto.LibroDto;
import com.p50.libroapp.exception.ResourceNotFoundException;
import com.p50.libroapp.model.Libro;
import com.p50.libroapp.response.PaginatedResponse;
import com.p50.libroapp.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping(value = "/libros", produces = "application/json")
    public ResponseEntity<PaginatedResponse> getAllLibros(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Libro> libros = libroService.findAll(page, size);
        long totalRegistros = libroService.countAllLibros();
        int totalPaginas = (int) Math.ceil((double) totalRegistros / size);

        PaginatedResponse response = new PaginatedResponse(libros, totalRegistros, totalPaginas);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/libros/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Libro libro = libroService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Libro no encontrado con ID: " + id));
        return ResponseEntity.ok(libro);
    }

    @PostMapping("/libros")
    public ResponseEntity<Libro> createLibro(@RequestBody LibroDto libroDto) {
        Libro libro = libroService.create(libroDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(libro.getId()).toUri();
        return ResponseEntity.created(location).body(libro);
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody LibroDto libroDto) {
        Libro libro = libroService.update(id, libroDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/desactivarLibro/{id}")
    public ResponseEntity<?> desactivarLibro(@PathVariable Long id) {
        libroService.deactivateLibro(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<?> deleteLibro(@PathVariable Long id) {
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}